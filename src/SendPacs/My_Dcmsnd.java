/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at http://sourceforge.net/projects/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Gunter Zeilinger, Huetteldorferstr. 24/10, 1150 Vienna/Austria/Europe.
 * Portions created by the Initial Developer are Copyright (C) 2002-2005
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * Gunter Zeilinger <gunterze@gmail.com>
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package SendPacs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.dcm4che2.data.BasicDicomObject;
import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.UID;
import org.dcm4che2.data.UIDDictionary;
import org.dcm4che2.data.VR;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.io.DicomOutputStream;
import org.dcm4che2.io.StopTagInputHandler;
import org.dcm4che2.io.TranscoderInputHandler;
import org.dcm4che2.net.Association;
import org.dcm4che2.net.CommandUtils;
import org.dcm4che2.net.ConfigurationException;
import org.dcm4che2.net.Device;
import org.dcm4che2.net.DimseRSP;
import org.dcm4che2.net.DimseRSPHandler;
import java.util.concurrent.Executor;
import org.dcm4che2.net.NetworkApplicationEntity;
import org.dcm4che2.net.NetworkConnection;
import org.dcm4che2.net.NewThreadExecutor;
import org.dcm4che2.net.NoPresentationContextException;
import org.dcm4che2.net.PDVOutputStream;
import org.dcm4che2.net.TransferCapability;
import org.dcm4che2.net.service.StorageCommitmentService;
import org.dcm4che2.util.StringUtils;
import org.dcm4che2.util.UIDUtils;

/**
 * @author gunter zeilinger(gunterze@gmail.com)
 * @version $Revision: 811 $ $Date: 2007-03-27 07:56:34 +0000 (Tue, 27 Mar 2007) $
 * @since Oct 13, 2005
 */
public class My_Dcmsnd extends StorageCommitmentService {

    private static final int KB = 1024;

    private static final int MB = KB * KB;

    private static final int PEEK_LEN = 1024;

    private static final String USAGE = 
        "dcmsnd [Options] <aet>[@<host>[:<port>]] <file>|<directory>...";

    private static final String DESCRIPTION = 
        "\nLoad composite DICOM Object(s) from specified DICOM file(s) and send it "
      + "to the specified remote Application Entity. If a directory is specified,"
      + "DICOM Object in files under that directory and further sub-directories "
      + "are sent. If <port> is not specified, DICOM default port 104 is assumed. "
      + "If also no <host> is specified, localhost is assumed. Optionally, a "
      + "Storage Commitment Request for successfully tranferred objects is sent "
      + "to the remote Application Entity after the storage. The Storage Commitment "
      + "result is accepted on the same association or - if a local port is "
      + "specified by option -L - in a separate association initiated by the "
      + "remote Application Entity\n"
      + "OPTIONS:";

    private static final String EXAMPLE = 
        "\nExample: dcmsnd -stgcmt -L DCMSND:11113 STORESCP@localhost:11112 image.dcm \n"
      + "=> Start listening on local port 11113 for receiving Storage Commitment "
      + "results, send DICOM object image.dcm to Application Entity STORESCP, "
      + "listening on local port 11112, and request Storage Commitment.";

    private static final String[] ONLY_IVLE_TS = { 
        UID.ImplicitVRLittleEndian
    };

    private static final String[] IVLE_TS = { 
        UID.ImplicitVRLittleEndian,
        UID.ExplicitVRLittleEndian, 
        UID.ExplicitVRBigEndian,
    };

    private static final String[] EVLE_TS = {
        UID.ExplicitVRLittleEndian,
        UID.ImplicitVRLittleEndian,
        UID.ExplicitVRBigEndian, 
    };

    private static final String[] EVBE_TS = { 
        UID.ExplicitVRBigEndian,
        UID.ExplicitVRLittleEndian, 
        UID.ImplicitVRLittleEndian, 
    };

    private static final int STG_CMT_ACTION_TYPE = 1;

    private Executor executor = new NewThreadExecutor("DCMSND");

    private NetworkApplicationEntity remoteAE = new NetworkApplicationEntity();

    private NetworkConnection remoteConn = new NetworkConnection();

    private Device device = new Device("DCMSND");

    private NetworkApplicationEntity ae = new NetworkApplicationEntity();

    private NetworkConnection conn = new NetworkConnection();

    private HashMap as2ts = new HashMap();

    private ArrayList files = new ArrayList();

    private Association assoc;

    private int priority = 0;
    
    private int transcoderBufferSize = 1024;

    private int filesSent = 0;

    private long totalSize = 0L;
    
    private boolean stgcmt = false;
    
    private long shutdownDelay = 1000L;
    
    private DicomObject stgCmtResult;
    
    public My_Dcmsnd() {
        remoteAE.setInstalled(true);
        remoteAE.setAssociationAcceptor(true);
        remoteAE.setNetworkConnection(new NetworkConnection[] { remoteConn });

        device.setNetworkApplicationEntity(ae);
        device.setNetworkConnection(conn);
        ae.setNetworkConnection(conn);
        ae.setAssociationInitiator(true);
        ae.setAssociationAcceptor(true);
        ae.register(this);
        ae.setAETitle("DCMSND");
    }

    public final void setLocalHost(String hostname) {
        conn.setHostname(hostname);
    }

    public final void setLocalPort(int port) {
        conn.setPort(port);
    }
    
    public final void setRemoteHost(String hostname) {
        remoteConn.setHostname(hostname);
    }

    public final void setRemotePort(int port) {
        remoteConn.setPort(port);
    }
    
    public final void setCalledAET(String called) {
        remoteAE.setAETitle(called);
    }

    public final void setCalling(String calling) {
        ae.setAETitle(calling);
    }
    
    public final void setStorageCommitment(boolean stgcmt) {
        this.stgcmt = stgcmt;
    }

    public final boolean isStorageCommitment() {
        return stgcmt;
    }
    
    public final void setShutdownDelay(int shutdownDelay) {
        this.shutdownDelay = shutdownDelay;
    }
    

    public final void setConnectTimeout(int connectTimeout) {
        conn.setConnectTimeout(connectTimeout);
    }

    public final void setMaxPDULengthReceive(int maxPDULength) {
        ae.setMaxPDULengthReceive(maxPDULength);
    }

    public final void setMaxOpsInvoked(int maxOpsInvoked) {
        ae.setMaxOpsInvoked(maxOpsInvoked);
    }

    public final void setPackPDV(boolean packPDV) {
        ae.setPackPDV(packPDV);
    }

    public final void setAssociationReaperPeriod(int period) {
        device.setAssociationReaperPeriod(period);
    }

    public final void setDimseRspTimeout(int timeout) {
        ae.setDimseRspTimeout(timeout);
    }

    public final void setPriority(int priority) {
        this.priority = priority;
    }

    public final void setTcpNoDelay(boolean tcpNoDelay) {
        conn.setTcpNoDelay(tcpNoDelay);
    }

    public final void setAcceptTimeout(int timeout) {
        conn.setAcceptTimeout(timeout);
    }

    public final void setReleaseTimeout(int timeout) {
        conn.setReleaseTimeout(timeout);
    }

    public final void setSocketCloseDelay(int timeout) {
        conn.setSocketCloseDelay(timeout);
    }

    public final void setMaxPDULengthSend(int maxPDULength) {
        ae.setMaxPDULengthSend(maxPDULength);
    }

    public final void setReceiveBufferSize(int bufferSize) {
        conn.setReceiveBufferSize(bufferSize);
    }

    public final void setSendBufferSize(int bufferSize) {
        conn.setSendBufferSize(bufferSize);
    }

    public final void setTranscoderBufferSize(int transcoderBufferSize) {
        this.transcoderBufferSize = transcoderBufferSize;
    }

    public final int getNumberOfFilesToSend() {
        return files.size();
    }

    public final int getNumberOfFilesSent() {
        return filesSent;
    }

    public final long getTotalSizeSent() {
        return totalSize;
    }

    private static CommandLine parse(String[] args) {
        Options opts = new Options();
        OptionBuilder.withArgName("aet[@host][:port]");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "set AET, local address and listening port of local Application Entity");
        opts.addOption(OptionBuilder.create("L"));

        opts.addOption("stgcmt", false,
                "request storage commitment of (successfully) sent objects afterwards");
        
        OptionBuilder.withArgName("maxops");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "maximum number of outstanding operations it may invoke " + 
                "asynchronously, unlimited by default.");
        opts.addOption(OptionBuilder.create("async"));

        opts.addOption("pdv1", false,
                "send only one PDV in one P-Data-TF PDU, " + 
                "pack command and data PDV in one P-DATA-TF PDU by default.");
        opts.addOption("tcpdelay", false,
                "set TCP_NODELAY socket option to false, true by default");

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "timeout in ms for TCP connect, no timeout by default");
        opts.addOption(OptionBuilder.create("connectTO"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "delay in ms for Socket close after sending A-ABORT, " +
                "50ms by default");
        opts.addOption(OptionBuilder.create("soclosedelay"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "delay in ms for closing the listening socket, " +
                "1000ms by default");
        opts.addOption(OptionBuilder.create("shutdowndelay"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "period in ms to check for outstanding DIMSE-RSP, " +
                "10s by default");
        opts.addOption(OptionBuilder.create("reaper"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "timeout in ms for receiving DIMSE-RSP, 60s by default");
        opts.addOption(OptionBuilder.create("rspTO"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "timeout in ms for receiving A-ASSOCIATE-AC, 5s by default");
        opts.addOption(OptionBuilder.create("acceptTO"));

        OptionBuilder.withArgName("ms");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "timeout in ms for receiving A-RELEASE-RP, 5s by default");
        opts.addOption(OptionBuilder.create("releaseTO"));

        OptionBuilder.withArgName("KB");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "maximal length in KB of received P-DATA-TF PDUs, 16KB by default");
        opts.addOption(OptionBuilder.create("rcvpdulen"));

        OptionBuilder.withArgName("KB");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "maximal length in KB of sent P-DATA-TF PDUs, 16KB by default");
        opts.addOption(OptionBuilder.create("sndpdulen"));

        OptionBuilder.withArgName("KB");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "set SO_RCVBUF socket option to specified value in KB");
        opts.addOption(OptionBuilder.create("sorcvbuf"));

        OptionBuilder.withArgName("KB");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "set SO_SNDBUF socket option to specified value in KB");
        opts.addOption(OptionBuilder.create("sosndbuf"));

        OptionBuilder.withArgName("KB");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(
                "transcoder buffer size in KB, 1KB by default");
        opts.addOption(OptionBuilder.create("bufsize"));

        opts.addOption("lowprior", false,
                "LOW priority of the C-STORE operation, MEDIUM by default");
        opts.addOption("highprior", false,
                "HIGH priority of the C-STORE operation, MEDIUM by default");
        opts.addOption("h", "help", false, "print this message");
        opts.addOption("V", "version", false,
                "print the version information and exit");
        CommandLine cl = null;
        try {
            cl = new GnuParser().parse(opts, args);
        } catch (ParseException e) {
            exit("dcmsnd: " + e.getMessage());
        }
        if (cl.hasOption('V')) {
            Package p = My_Dcmsnd.class.getPackage();
            System.out.println("dcmsnd v" + p.getImplementationVersion());
            System.exit(0);
        }
        if (cl.hasOption('h') || cl.getArgList().size() < 2) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(USAGE, DESCRIPTION, opts, EXAMPLE);
            System.exit(0);
        }
        return cl;
    }

    //<editor-fold defaultstate="collapsed" desc="NO-USE-MAIN...">
    //    public static void main(String[] args) {
//        CommandLine cl = parse(args);
//        My_Dcmsnd dcmsnd = new My_Dcmsnd();
//        final List argList = cl.getArgList();
//        String remoteAE = (String) argList.get(0);
//        String[] calledAETAddress = split(remoteAE, '@');
//        dcmsnd.setCalledAET(calledAETAddress[0]);
//        if (calledAETAddress[1] == null) {
//            dcmsnd.setRemoteHost("127.0.0.1");
//            dcmsnd.setRemotePort(104);
//        } else {
//            String[] hostPort = split(calledAETAddress[1], ':');
//            dcmsnd.setRemoteHost(hostPort[0]);
//            dcmsnd.setRemotePort(toPort(hostPort[1]));
//        }
//        if (cl.hasOption("L")) {
//            String localAE = (String) cl.getOptionValue("L");
//            String[] localPort = split(localAE, ':');
//            if (localPort[1] != null) {
//                dcmsnd.setLocalPort(toPort(localPort[1]));                
//            }
//            String[] callingAETHost = split(localPort[0], '@');
//            dcmsnd.setCalling(callingAETHost[0]);
//            if (callingAETHost[1] != null) {
//                dcmsnd.setLocalHost(callingAETHost[0]);
//            }
//        }
//        dcmsnd.setStorageCommitment(cl.hasOption("stgcmt"));
//        if (cl.hasOption("connectTO"))
//            dcmsnd.setConnectTimeout(parseInt(cl.getOptionValue("connectTO"),
//                    "illegal argument of option -connectTO", 1,
//                    Integer.MAX_VALUE));
//        if (cl.hasOption("reaper"))
//            dcmsnd.setAssociationReaperPeriod(
//                    parseInt(cl.getOptionValue("reaper"),
//                    "illegal argument of option -reaper",
//                    1, Integer.MAX_VALUE));
//        if (cl.hasOption("rspTO"))
//            dcmsnd.setDimseRspTimeout(parseInt(cl.getOptionValue("rspTO"),
//                    "illegal argument of option -rspTO",
//                    1, Integer.MAX_VALUE));
//        if (cl.hasOption("acceptTO"))
//            dcmsnd.setAcceptTimeout(parseInt(cl.getOptionValue("acceptTO"),
//                    "illegal argument of option -acceptTO", 
//                    1, Integer.MAX_VALUE));
//        if (cl.hasOption("releaseTO"))
//            dcmsnd.setReleaseTimeout(parseInt(cl.getOptionValue("releaseTO"),
//                    "illegal argument of option -releaseTO",
//                    1, Integer.MAX_VALUE));
//        if (cl.hasOption("soclosedelay"))
//            dcmsnd.setSocketCloseDelay(
//                    parseInt(cl.getOptionValue("soclosedelay"),
//                    "illegal argument of option -soclosedelay", 1, 10000));
//        if (cl.hasOption("shutdowndelay"))
//            dcmsnd.setShutdownDelay(
//                    parseInt(cl.getOptionValue("shutdowndelay"),
//                    "illegal argument of option -shutdowndelay", 1, 10000));
//        if (cl.hasOption("rcvpdulen"))
//            dcmsnd.setMaxPDULengthReceive(
//                    parseInt(cl.getOptionValue("rcvpdulen"),
//                    "illegal argument of option -rcvpdulen", 1, 10000) * KB);
//        if (cl.hasOption("sndpdulen"))
//            dcmsnd.setMaxPDULengthSend(parseInt(cl.getOptionValue("sndpdulen"),
//                    "illegal argument of option -sndpdulen", 1, 10000) * KB);
//        if (cl.hasOption("sosndbuf"))
//            dcmsnd.setSendBufferSize(parseInt(cl.getOptionValue("sosndbuf"),
//                    "illegal argument of option -sosndbuf", 1, 10000) * KB);
//        if (cl.hasOption("sorcvbuf"))
//            dcmsnd.setReceiveBufferSize(parseInt(cl.getOptionValue("sorcvbuf"),
//                    "illegal argument of option -sorcvbuf", 1, 10000) * KB);
//        if (cl.hasOption("bufsize"))
//            dcmsnd.setTranscoderBufferSize(
//                    parseInt(cl.getOptionValue("bufsize"),
//                    "illegal argument of option -bufsize", 1, 10000) * KB);
//        dcmsnd.setPackPDV(!cl.hasOption("pdv1"));
//        dcmsnd.setTcpNoDelay(!cl.hasOption("tcpdelay"));
//        if (cl.hasOption("async"))
//            dcmsnd.setMaxOpsInvoked(parseInt(cl.getOptionValue("async"),
//                    "illegal argument of option -async", 0, 0xffff));
//        if (cl.hasOption("lowprior"))
//            dcmsnd.setPriority(CommandUtils.LOW);
//        if (cl.hasOption("highprior"))
//            dcmsnd.setPriority(CommandUtils.HIGH);
//        System.out.println("Scanning files to send");
//        long t1 = System.currentTimeMillis();
//        for (int i = 1, n = argList.size(); i < n; ++i)
//            dcmsnd.addFile(new File((String) argList.get(i)));
//        long t2 = System.currentTimeMillis();
//        if (dcmsnd.getNumberOfFilesToSend() == 0) {
//            System.exit(2);
//        }
//        System.out.println("\nScanned " + dcmsnd.getNumberOfFilesToSend()
//                + " files in " + ((t2 - t1) / 1000F) + "s (="
//                + ((t2 - t1) / dcmsnd.getNumberOfFilesToSend()) + "ms/file)");
//        dcmsnd.configureTransferCapability();
//        try {
//            dcmsnd.start();
//        } catch (Exception e) {
//            System.err.println("ERROR: Failed to start server for receiving " +
//                    "Storage Commitment results:" + e.getMessage());
//            System.exit(2);
//        }
//        try {
//            t1 = System.currentTimeMillis();
//            try {
//                dcmsnd.open();
//            } catch (Exception e) {
//                System.err.println("ERROR: Failed to establish association:"
//                        + e.getMessage());
//                System.exit(2);
//            }
//            t2 = System.currentTimeMillis();
//            System.out.println("Connected to " + remoteAE + " in " 
//                    + ((t2 - t1) / 1000F) + "s");
//    
//            t1 = System.currentTimeMillis();
//            dcmsnd.send();
//            t2 = System.currentTimeMillis();
//            prompt(dcmsnd, (t2 - t1) / 1000F);
//            if (dcmsnd.isStorageCommitment()) {
//                t1 = System.currentTimeMillis();
//                if (dcmsnd.commit()) {
//                    t2 = System.currentTimeMillis();
//                    System.out.println("Request Storage Commitment from " 
//                            + remoteAE + " in " + ((t2 - t1) / 1000F) + "s");
//                    System.out.println("Waiting for Storage Commitment Result..");
//                    try {
//                        DicomObject cmtrslt = dcmsnd.waitForStgCmtResult();
//                        t1 = System.currentTimeMillis();
//                        promptStgCmt(cmtrslt, ((t1 - t2) / 1000F));
//                    } catch (InterruptedException e) {
//                        System.err.println("ERROR:" + e.getMessage());
//                    }
//                }
//             }
//            dcmsnd.close();
//            System.out.println("Released connection to " + remoteAE);
//        } finally {
//            dcmsnd.stop();
//        }
//    }
    //</editor-fold>

    private static void promptStgCmt(DicomObject cmtrslt, float seconds) {
        System.out.println("Received Storage Commitment Result after "
                + seconds + "s:");
        DicomElement refSOPSq = cmtrslt.get(Tag.ReferencedSOPSequence);
        System.out.print(refSOPSq.countItems());
        System.out.println(" successful");
        DicomElement failedSOPSq = cmtrslt.get(Tag.FailedSOPSequence);
        if (failedSOPSq != null) {
            System.out.print(failedSOPSq.countItems());
            System.out.println(" FAILED!");                       
        }
    }

    //Private to Protected
    protected synchronized DicomObject waitForStgCmtResult() throws InterruptedException {
        while (stgCmtResult == null) wait();
        return stgCmtResult;
    }

    private static void prompt(My_Dcmsnd dcmsnd, float seconds) {
        System.out.print("\nSent ");
        System.out.print(dcmsnd.getNumberOfFilesSent());
        System.out.print(" objects (=");
        promptBytes(dcmsnd.getTotalSizeSent());
        System.out.print(") in ");
        System.out.print(seconds);
        System.out.print("s (=");
        promptBytes(dcmsnd.getTotalSizeSent() / seconds);
        System.out.println("/s)");
    }

    private static void promptBytes(float totalSizeSent) {
        if (totalSizeSent > MB) {
            System.out.print(totalSizeSent / MB);
            System.out.print("MB");
        } else {
            System.out.print(totalSizeSent / KB);
            System.out.print("KB");
        }
    }

    private static int toPort(String port) {
        return port != null ? parseInt(port, "illegal port number", 1, 0xffff)
                : 104;
    }

    private static String[] split(String s, char delim) {
        String[] s2 = { s, null };
        int pos = s.indexOf(delim);
        if (pos != -1) {
            s2[0] = s.substring(0, pos);
            s2[1] = s.substring(pos + 1);
        }
        return s2;
    }

    private static void exit(String msg) {
        System.err.println(msg);
        System.err.println("Try 'dcmsnd -h' for more information.");
        System.exit(1);
    }

    private static int parseInt(String s, String errPrompt, int min, int max) {
        try {
            int i = Integer.parseInt(s);
            if (i >= min && i <= max)
                return i;
        } catch (NumberFormatException e) {
        }
        exit(errPrompt);
        throw new RuntimeException();
    }

    public void addFile(File f) {
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (int i = 0; i < fs.length; i++)
                addFile(fs[i]);
            return;
        }
        FileInfo info = new FileInfo(f);
        DicomObject dcmObj = new BasicDicomObject();
        try {
            DicomInputStream in = new DicomInputStream(f);
            try {
                in.setHandler(new StopTagInputHandler(Tag.StudyDate));
                in.readDicomObject(dcmObj, PEEK_LEN);
                info.tsuid = in.getTransferSyntax().uid();
                info.fmiEndPos = in.getEndOfFileMetaInfoPosition();
            } finally {
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("WARNING: Failed to parse " + f + " - skipped.");
            System.out.print('F');
            return;
        }
        info.cuid = dcmObj.getString(Tag.SOPClassUID);
        if (info.cuid == null) {
            System.err.println("WARNING: Missing SOP Class UID in " + f
                    + " - skipped.");
            System.out.print('F');
            return;
        }
        info.iuid = dcmObj.getString(Tag.SOPInstanceUID);
        if (info.iuid == null) {
            System.err.println("WARNING: Missing SOP Instance UID in " + f
                    + " - skipped.");
            System.out.print('F');
            return;
        }
        addTransferCapability(info.cuid, info.tsuid);
        files.add(info);
        System.out.print('.');
    }

    private void addTransferCapability(String cuid, String tsuid) {
        HashSet ts = (HashSet) as2ts.get(cuid);
        if (ts == null) {
            ts = new HashSet();
            ts.add(UID.ImplicitVRLittleEndian);
            as2ts.put(cuid, ts);
        }
        ts.add(tsuid);
    }

    //Private to protected
    protected void configureTransferCapability() {
        int off = stgcmt ? 1 : 0;
        TransferCapability[] tc = new TransferCapability[off + as2ts.size()];
        if (stgcmt) {
            tc[0] = new TransferCapability(
                    UID.StorageCommitmentPushModelSOPClass,
                    ONLY_IVLE_TS, 
                    TransferCapability.SCU);
        }
        Iterator iter = as2ts.entrySet().iterator();
        for (int i = off; i < tc.length; i++) {
            Map.Entry e = (Map.Entry) iter.next();
            String cuid = (String) e.getKey();
            HashSet ts = (HashSet) e.getValue();
            tc[i] = new TransferCapability(cuid, 
                    (String[]) ts.toArray(new String[ts.size()]),
                    TransferCapability.SCU);
        }
        ae.setTransferCapability(tc);
    }

    public void start() throws IOException { 
        if (conn.isListening()) {
            conn.bind(executor );
            System.out.println("Start Server listening on port " + conn.getPort());
        }
    }

    public void stop() {
        if (conn.isListening()) {
            try {
                Thread.sleep(shutdownDelay);
            } catch (InterruptedException e) {
                // Should not happen
                e.printStackTrace();                
            }
            conn.unbind();
        }
    }
    
    public void open() throws IOException, ConfigurationException,
            InterruptedException {
        assoc = ae.connect(remoteAE, executor);
    }

    public void send() {
        for (int i = 0, n = files.size(); i < n; ++i) {
            FileInfo info = (FileInfo) files.get(i);
            TransferCapability tc = assoc.getTransferCapabilityAsSCU(info.cuid);
            if (tc == null) {
                System.out.println();
                System.out.println(UIDDictionary.getDictionary().prompt(
                        info.cuid)
                        + " not supported by " + remoteAE.getAETitle());
                System.out.println("skip file " + info.f);
                continue;
            }
            String tsuid = selectTransferSyntax(tc.getTransferSyntax(),
                    info.tsuid);
            if (tsuid == null) {
                System.out.println();
                System.out.println(UIDDictionary.getDictionary().prompt(
                        info.cuid)
                        + " with "
                        + UIDDictionary.getDictionary().prompt(info.tsuid)
                        + " not supported by" + remoteAE.getAETitle());
                System.out.println("skip file " + info.f);
                continue;
            }
            try {
                DimseRSPHandler rspHandler = new DimseRSPHandler() {
                    public void onDimseRSP(Association as, DicomObject cmd,
                            DicomObject data) {
                        My_Dcmsnd.this.onDimseRSP(as, cmd, data);
                    }
                };

                assoc.cstore(info.cuid, info.iuid, priority,
                        new DataWriter(info), tsuid, rspHandler);
            } catch (NoPresentationContextException e) {
                System.err.println("WARNING: " + e.getMessage()
                        + " - cannot send " + info.f);
                System.out.print('F');
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ERROR: Failed to send - " + info.f + ": "
                        + e.getMessage());
                System.out.print('F');
            } catch (InterruptedException e) {
                // should not happen
                e.printStackTrace();
            }
        }
        try {
            assoc.waitForDimseRSP();
        } catch (InterruptedException e) {
            // should not happen
            e.printStackTrace();
        }
    }

    public boolean commit() {
        DicomObject actionInfo = new BasicDicomObject();
        actionInfo.putString(Tag.TransactionUID, VR.UI, UIDUtils.createUID());
        DicomElement refSOPSq = actionInfo.putSequence(Tag.ReferencedSOPSequence);
        for (int i = 0, n = files.size(); i < n; ++i) {
            FileInfo info = (FileInfo) files.get(i);
            if (info.transferred) {
                BasicDicomObject refSOP = new BasicDicomObject();
                refSOP.putString(Tag.ReferencedSOPClassUID, VR.UI, info.cuid);
                refSOP.putString(Tag.ReferencedSOPInstanceUID, VR.UI, info.iuid);
                refSOPSq.addDicomObject(refSOP);
            }
        }
        try {
            DimseRSP rsp = assoc.naction(UID.StorageCommitmentPushModelSOPClass,
                UID.StorageCommitmentPushModelSOPInstance, STG_CMT_ACTION_TYPE,
                actionInfo, UID.ImplicitVRLittleEndian);
            rsp.next();
            
            DicomObject cmd = rsp.getCommand();
            
            System.out.println(cmd);
            
            int status = cmd.getInt(Tag.Status);
            if (status == 0) {
                return true;
            }
            
            PacsManager.getInstance().setProgramStatus(status); //Return status to manager            
            
            System.err.println(
                    "WARNING: Storage Commitment request failed with status: "
                    + StringUtils.shortToHex(status) + "H");
            System.err.println(cmd.toString());
        } catch (NoPresentationContextException e) {
            System.err.println("WARNING: " + e.getMessage()
                    + " - cannot request Storage Commitment");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(
                    "ERROR: Failed to send Storage Commitment request: "
                    + e.getMessage());
        } catch (InterruptedException e) {
            // should not happen
            e.printStackTrace();
        }
        return false;
    }
    
    private String selectTransferSyntax(String[] available, String tsuid) {
        if (tsuid.equals(UID.ImplicitVRLittleEndian))
            return selectTransferSyntax(available, IVLE_TS);
        if (tsuid.equals(UID.ExplicitVRLittleEndian))
            return selectTransferSyntax(available, EVLE_TS);
        if (tsuid.equals(UID.ExplicitVRBigEndian))
            return selectTransferSyntax(available, EVBE_TS);
        return tsuid;
    }

    private String selectTransferSyntax(String[] available, String[] tsuids) {
        for (int i = 0; i < tsuids.length; i++)
            for (int j = 0; j < available.length; j++)
                if (available[j].equals(tsuids[i]))
                    return available[j];
        return null;
    }

    public void close() {
        try {
            assoc.release(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final class FileInfo {
        File f;

        String cuid;

        String iuid;

        String tsuid;

        long fmiEndPos;

        long length;
        
        boolean transferred;

        public FileInfo(File f) {
            this.f = f;
            this.length = f.length();
        }
                
    }

    private class DataWriter implements org.dcm4che2.net.DataWriter {

        private FileInfo info;

        public DataWriter(FileInfo info) {
            this.info = info;
        }

        public void writeTo(PDVOutputStream out, String tsuid)
                throws IOException {
            if (tsuid.equals(info.tsuid)) {
                FileInputStream fis = new FileInputStream(info.f);
                try {
                    long skip = info.fmiEndPos;
                    while (skip > 0)
                        skip -= fis.skip(skip);
                    out.copyFrom(fis);
                } finally {
                    fis.close();
                }
            } else {
                DicomInputStream dis = new DicomInputStream(info.f);
                try {
                    DicomOutputStream dos = new DicomOutputStream(out);
                    dos.setTransferSyntax(tsuid);
                    TranscoderInputHandler h = new TranscoderInputHandler(dos,
                            transcoderBufferSize);
                    dis.setHandler(h);
                    dis.readDicomObject();
                } finally {
                    dis.close();
                }
            }
        }

    }

    private void promptErrRSP(String prefix, int status, FileInfo info,
            DicomObject cmd) {
        System.err.println(prefix + StringUtils.shortToHex(status) + "H for "
                + info.f + ", cuid=" + info.cuid + ", tsuid=" + info.tsuid);
        System.err.println(cmd.toString());
    }

    private void onDimseRSP(Association as, DicomObject cmd, DicomObject data) {
        int status = cmd.getInt(Tag.Status);
        int msgId = cmd.getInt(Tag.MessageIDBeingRespondedTo);
        FileInfo info = (FileInfo) files.get(msgId - 1);
        switch (status) {
        case 0:
            info.transferred = true;
            totalSize += info.length;
            ++filesSent;
            System.out.print('.');
            break;
        case 0xB000:
        case 0xB006:
        case 0xB007:
            info.transferred = true;
            totalSize += info.length;
            ++filesSent;
            promptErrRSP("WARNING: Received RSP with Status ", status, info,
                    cmd);
            System.out.print('W');
            break;
        default:
            promptErrRSP("ERROR: Received RSP with Status ", status, info, cmd);
            System.out.print('F');
        }
    }
    
    protected synchronized void onNEventReportRSP(Association as, int pcid,
            DicomObject rq, DicomObject info, DicomObject rsp) {
        stgCmtResult = info;
        notifyAll();
    }
}