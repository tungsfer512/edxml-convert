package vn.ript.api.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.io.Files;
import com.vnpt.xml.base.Content;
import com.vnpt.xml.base.attachment.Attachment;
import com.vnpt.xml.base.header.Bussiness;
import com.vnpt.xml.base.header.BussinessDocumentInfo;
import com.vnpt.xml.base.header.Header;
import com.vnpt.xml.base.header.KeyInfo;
import com.vnpt.xml.base.header.OrganIdList;
import com.vnpt.xml.base.header.Organization;
import com.vnpt.xml.base.header.Receiver;
import com.vnpt.xml.base.header.ReceiverList;
import com.vnpt.xml.base.header.ReplacementInfo;
import com.vnpt.xml.base.header.ReplacementInfoList;
import com.vnpt.xml.base.header.ResponseFor;
import com.vnpt.xml.base.header.SignReference;
import com.vnpt.xml.base.header.Signature;
import com.vnpt.xml.base.header.SignedInfo;
import com.vnpt.xml.base.header.SignerInfo;
import com.vnpt.xml.base.header.StaffInfo;
import com.vnpt.xml.base.header.TraceHeader;
import com.vnpt.xml.base.header.TraceHeaderList;
import com.vnpt.xml.base.header.X509Data;
import com.vnpt.xml.base.util.DateUtils;
import com.vnpt.xml.ed.Ed;
import com.vnpt.xml.ed.builder.EdXmlBuilder;
import com.vnpt.xml.ed.header.Code;
import com.vnpt.xml.ed.header.DocumentType;
import com.vnpt.xml.ed.header.MessageHeader;
import com.vnpt.xml.ed.header.OtherInfo;
import com.vnpt.xml.ed.header.PromulgationInfo;
import com.vnpt.xml.status.Status;
import com.vnpt.xml.status.builder.StatusXmlBuilder;
import com.vnpt.xml.status.header.MessageStatus;

import jakarta.activation.MimetypesFileTypeMap;

public class EdXMLBuild {

    public static Content create_edoc_new(
            String header_messageheader_from_organid,
            String header_messageheader_from_organizationincharge,
            String header_messageheader_from_organname,
            String header_messageheader_from_organadd,
            String header_messageheader_from_email,
            String header_messageheader_from_telephone,
            String header_messageheader_from_fax,
            String header_messageheader_from_website,
            List<JSONObject> header_messageheader_toes,
            String header_messageheader_code_codenumber,
            String header_messageheader_code_codenotation,
            String header_messageheader_promulgationinfo_place,
            String header_messageheader_promulgationinfo_promulgationdate,
            String header_messageheader_documenttype_type,
            String header_messageheader_documenttype_typename,
            String header_messageheader_subject,
            String header_messageheader_content,
            String header_messageheader_signerinfo_competence,
            String header_messageheader_signerinfo_position,
            String header_messageheader_signerinfo_fullname,
            String header_messageheader_duedate,
            List<String> header_messageheader_toplaces,
            String header_messageheader_otherinfo_priority,
            String header_messageheader_otherinfo_sphereofpromulgation,
            String header_messageheader_otherinfo_typernotation,
            String header_messageheader_otherinfo_promulgationamount,
            String header_messageheader_otherinfo_pageamount,
            List<String> header_messageheader_otherinfo_appendixes,
            List<JSONObject> header_messageheader_responsefors,
            String header_messageheader_steeringtype,
            String header_messageheader_documentid,
            List<JSONObject> header_traceheaderlist_traceheader,
            String header_traceheaderlist_bussiness_bussinessdoctype,
            String header_traceheaderlist_bussiness_bussinessdocreason,
            List<JSONObject> header_traceheaderlist_bussiness_bussinessdocumentinfo,
            String header_traceheaderlist_bussiness_documentid,
            List<JSONObject> header_traceheaderlist_bussiness_staffinfoes,
            String header_traceheaderlist_bussiness_paper,
            List<JSONArray> header_traceheaderlist_bussiness_replacementinfolist_replacementinfo,
            String header_signature_signatureinfo_canonicalizationmethod,
            String header_signature_signatureinfo_signaturemethod,
            List<JSONObject> header_signature_signatureinfo_references,
            String header_signature_signaturevalue,
            String header_signature_keyinfo_x509data_x509subjectname,
            String header_signature_keyinfo_x509data_x509certificate,
            JSONObject attachments_metadata,
            List<File> attachments_file) {
        try {
            Ed ed = new Ed();
            Header header = new Header();
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.setFrom(new Organization(
                    header_messageheader_from_organid,
                    header_messageheader_from_organizationincharge,
                    header_messageheader_from_organname,
                    header_messageheader_from_organadd,
                    header_messageheader_from_email,
                    header_messageheader_from_telephone,
                    header_messageheader_from_fax,
                    header_messageheader_from_website));
            if (header_messageheader_toes != null) {
                for (JSONObject obj : header_messageheader_toes) {
                    messageHeader.addTo(new Organization(
                            obj.getString("organid"),
                            obj.getString("organizationincharge"),
                            obj.getString("organname"),
                            obj.getString("organadd"),
                            obj.getString("email"),
                            obj.getString("telephone"),
                            obj.getString("fax"),
                            obj.getString("website")));
                }
            }
            messageHeader.setCode(new Code(
                    header_messageheader_code_codenumber,
                    header_messageheader_code_codenotation));
            Date promulgationdate = null;
            if (header_messageheader_promulgationinfo_promulgationdate != null) {
                promulgationdate = Utils.YYYY_MM_DD_TO_DATE(header_messageheader_promulgationinfo_promulgationdate);
            }
            messageHeader.setPromulgationInfo(new PromulgationInfo(
                    header_messageheader_promulgationinfo_place, promulgationdate));
            messageHeader.setDocumentType(new DocumentType(
                    Integer.parseInt(header_messageheader_documenttype_type),
                    header_messageheader_documenttype_typename));
            messageHeader.setSubject(header_messageheader_subject);
            messageHeader.setContent(header_messageheader_content);
            messageHeader.setSignerInfo(new SignerInfo(
                    header_messageheader_signerinfo_competence,
                    header_messageheader_signerinfo_position,
                    header_messageheader_signerinfo_fullname));
            Date duedate = new Date();
            if (header_messageheader_duedate != null) {
                duedate = Utils.YYYY_MM_DD_TO_DATE(header_messageheader_duedate);
            }
            messageHeader.setDueDate(duedate);
            messageHeader.setToPlaces(header_messageheader_toplaces);
            OtherInfo otherInfo = new OtherInfo();
            otherInfo.setPriority(Integer.parseInt(header_messageheader_otherinfo_priority));
            otherInfo.setSphereOfPromulgation(header_messageheader_otherinfo_sphereofpromulgation);
            otherInfo.setTyperNotation(header_messageheader_otherinfo_typernotation);
            otherInfo.setPromulgationAmount(Integer.parseInt(header_messageheader_otherinfo_promulgationamount));
            otherInfo.setPageAmount(Integer.parseInt(header_messageheader_otherinfo_pageamount));
            otherInfo.setAppendixes(header_messageheader_otherinfo_appendixes);
            messageHeader.setOtherInfo(otherInfo);
            if (header_messageheader_responsefors != null) {
                for (JSONObject obj : header_messageheader_responsefors) {
                    Date obj_promulgationdate = null;
                    if (obj.has("promulgationdate")) {
                        obj_promulgationdate = Utils
                                .YYYY_MM_DD_TO_DATE(header_messageheader_promulgationinfo_promulgationdate);
                    }
                    messageHeader.addResponseFor(new ResponseFor(
                            obj.getString("organid"),
                            obj.getString("code"),
                            obj_promulgationdate,
                            obj.getString("documentid")));
                }
            }
            messageHeader.setSteeringType(Integer.parseInt(header_messageheader_steeringtype));
            messageHeader.setDocumentId(header_messageheader_documentid);
            header.setMessageHeader(messageHeader);
            TraceHeaderList traceHeaderList = new TraceHeaderList();
            if (header_traceheaderlist_traceheader != null) {
                for (JSONObject obj : header_traceheaderlist_traceheader) {
                    TraceHeader traceHeader = new TraceHeader();
                    traceHeader.setOrganId(obj.getString("organid"));
                    traceHeader
                            .setTimestamp(
                                    DateUtils.parse(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date())));
                    traceHeaderList.addTraceHeader(traceHeader);
                }
            }
            Bussiness bussiness = new Bussiness();
            bussiness.setBussinessDocType(header_traceheaderlist_bussiness_bussinessdoctype);
            bussiness.setBussinessDocReason(header_traceheaderlist_bussiness_bussinessdocreason);
            if (header_traceheaderlist_bussiness_bussinessdocumentinfo != null) {
                for (JSONObject obj : header_traceheaderlist_bussiness_bussinessdocumentinfo) {
                    BussinessDocumentInfo bussinessDocumentInfo = new BussinessDocumentInfo();
                    bussinessDocumentInfo.setdocumentInfo(obj.getString("documentinfo"));
                    bussinessDocumentInfo.setdocumentReceiver(obj.getString("documentreceiver"));
                    ReceiverList receiverList = new ReceiverList();
                    JSONArray receiverJsonList = obj.getJSONArray("receiverlist");
                    for (Object obj2 : receiverJsonList) {
                        JSONObject jsonObj2 = (JSONObject) obj2;
                        Receiver receiver = new Receiver();
                        JSONArray receiverJson = jsonObj2.getJSONArray("receiver");
                        for (Object obj3 : receiverJson) {
                            JSONObject jsonObj3 = (JSONObject) obj3;
                            receiver.setOrganId(jsonObj3.getString("organid"));
                            receiver.setReceiverType(jsonObj3.getString("receivertype"));
                            receiverList.addReceiver(receiver);
                        }
                        bussinessDocumentInfo.addreceiverList(receiverList);
                    }
                    bussiness.addBussinessDocumentInfo(bussinessDocumentInfo);
                }
            }
            bussiness.setDocumentId(header_traceheaderlist_bussiness_documentid);
            if (header_traceheaderlist_bussiness_staffinfoes != null) {
                for (JSONObject obj : header_traceheaderlist_bussiness_staffinfoes) {
                    StaffInfo staffInfo = new StaffInfo();
                    staffInfo.setDepartment(obj.getString("department"));
                    staffInfo.setStaff(obj.getString("staff"));
                    staffInfo.setMobile(obj.getString("mobile"));
                    staffInfo.setEmail(obj.getString("email"));
                    bussiness.addStaffInfo(staffInfo);
                }
            }
            bussiness.setPaper(header_traceheaderlist_bussiness_paper);
            if (header_traceheaderlist_bussiness_replacementinfolist_replacementinfo != null) {
                for (JSONArray obj : header_traceheaderlist_bussiness_replacementinfolist_replacementinfo) {
                    ReplacementInfoList replacementInfoList = new ReplacementInfoList();
                    for (Object obj2 : obj) {
                        JSONObject jsonObj2 = (JSONObject) obj2;
                        ReplacementInfo replacementInfo = new ReplacementInfo();
                        replacementInfo.setDocumentId(jsonObj2.getString("documentid"));
                        JSONArray organIdJsonList = jsonObj2.getJSONArray("organidlist");
                        OrganIdList organIdList = new OrganIdList();
                        for (Object obj3 : organIdJsonList) {
                            organIdList.addOrganId((String) obj3);
                        }
                        replacementInfo.setOrganIdList(organIdList);
                        replacementInfoList.addReplacementInfo(replacementInfo);
                    }
                    bussiness.addReplacementInfoList(replacementInfoList);
                }
            }
            traceHeaderList.setBussiness(bussiness);
            header.setTraceHeaderList(traceHeaderList);
            Signature signature = new Signature();
            signature.setSignatureValue(header_signature_signaturevalue);
            KeyInfo keyInfo = new KeyInfo();
            X509Data x509Data = new X509Data();
            x509Data.setX509Certificate(header_signature_keyinfo_x509data_x509certificate);
            x509Data.setX509SubjectName(header_signature_keyinfo_x509data_x509subjectname);
            keyInfo.setX509Data(x509Data);
            signature.setKeyInfo(keyInfo);
            SignedInfo signedInfo = new SignedInfo();
            signedInfo.setCanonicalizationMethod(header_signature_signatureinfo_canonicalizationmethod);
            signedInfo.setSignatureMethod(header_signature_signatureinfo_signaturemethod);
            for (JSONObject obj : header_signature_signatureinfo_references) {
                SignReference signReference = new SignReference();
                signReference.setDigestMethod(obj.getString("digestmethod"));
                signReference.setDigestValue(obj.getString("digestvalue"));
                signReference.setURI(obj.getString("uri"));
                JSONArray transforms = obj.getJSONArray("transforms");
                for (Object obj2 : transforms) {
                    signReference.addToTransform((String) obj2);
                }
                signedInfo.addReference(signReference);
            }
            signature.setSignedInfo(signedInfo);
            header.setSignature(signature);
            ed.setHeader(header);
            if (attachments_metadata != null && attachments_file.size() > 0) {
                List<Attachment> attachments = new ArrayList<Attachment>();
                for (File file : attachments_file) {
                    String[] attachmentnamesWithUUID = file.getName().split("\\.");
                    String[] attachmentnames = Arrays.copyOfRange(attachmentnamesWithUUID, 1,
                            attachmentnamesWithUUID.length);
                    String attachmentname = String.join(".", attachmentnames);
                    String contentid = Utils.UUID();
                    JSONObject attachment_metadata = attachments_metadata.getJSONObject(attachmentname);
                    String description = attachment_metadata.getString("description");
                    MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
                    String contenttype = fileTypeMap.getContentType(file);
                    Attachment att = new Attachment();
                    att.setContentId(contentid);
                    att.setContent(file);
                    att.setName(attachmentname);
                    att.setDescription(description);
                    att.setFormat(Files.getFileExtension(attachmentname));
                    att.setContentType(contenttype);
                    attachments.add(att);
                }
                ed.setAttachments(attachments);
            }
            Content content = EdXmlBuilder.build(ed, Utils.FILE_DIR);

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Content create_status(
            String responsefor_organid,
            String responsefor_code,
            String responsefor_promulgationdate,
            String responsefor_documentid,
            String from_organid,
            String from_organizationincharge,
            String from_organname,
            String from_organadd,
            String from_email,
            String from_telephone,
            String from_fax,
            String from_website,
            String statuscode,
            String description,
            String staffinfo_department,
            String staffinfo_staff,
            String staffinfo_mobile,
            String staffinfo_email) {
        try {
            Header header = new Header();
            MessageStatus msgStatus = new MessageStatus();
            Date responsefor_promulgationdate_te = new Date();
            if (responsefor_promulgationdate != null) {
                responsefor_promulgationdate_te = Utils.YYYY_MM_DD_TO_DATE(responsefor_promulgationdate);
            }
            msgStatus.setResponseFor(new ResponseFor(
                    responsefor_organid,
                    responsefor_code,
                    responsefor_promulgationdate_te,
                    responsefor_documentid));
            msgStatus.setFrom(new Organization(
                    from_organid,
                    from_organizationincharge,
                    from_organname,
                    from_organadd,
                    from_email,
                    from_telephone,
                    from_fax,
                    from_website));
            msgStatus.setStatusCode(statuscode);
            msgStatus.setDescription(description);
            msgStatus.setTimestamp(new Date());
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setDepartment(staffinfo_department);
            staffInfo.setStaff(staffinfo_staff);
            staffInfo.setEmail(staffinfo_email);
            staffInfo.setMobile(staffinfo_mobile);
            msgStatus.setStaffInfo(staffInfo);
            header.setMessageHeader(msgStatus);
            Content content = null;
            content = StatusXmlBuilder.build(new Status(header), Utils.FILE_DIR);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}