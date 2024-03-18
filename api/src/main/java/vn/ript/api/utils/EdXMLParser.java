package vn.ript.api.utils;

import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vnpt.xml.base.attachment.Attachment;
import com.vnpt.xml.base.body.Body;
import com.vnpt.xml.base.body.Manifest;
import com.vnpt.xml.base.body.Reference;
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
import com.vnpt.xml.status.Status;
import com.vnpt.xml.status.header.MessageStatus;
import com.vnpt.xml.status.parser.StatusXmlParser;

import com.vnpt.xml.ed.Ed;
import com.vnpt.xml.ed.header.Code;
import com.vnpt.xml.ed.header.DocumentType;
import com.vnpt.xml.ed.header.MessageHeader;
import com.vnpt.xml.ed.header.OtherInfo;
import com.vnpt.xml.ed.header.PromulgationInfo;
import com.vnpt.xml.ed.parser.EdXmlParser;

public class EdXMLParser {

    public static JSONObject parseStatus(InputStream inputStream) {
        try {
            JSONObject jsonObject = new JSONObject();
            Status status = StatusXmlParser.parse(inputStream);
            if (status == null) {
                return null;
            }
            Header header = status.getHeader();
            if (header == null) {
                return null;
            }
            MessageStatus messageStatus = (MessageStatus) header.getMessageHeader();
            if (messageStatus == null) {
                return null;
            }
            ResponseFor responseFor = messageStatus.getResponseFor();
            Organization from = messageStatus.getFrom();
            String statuscode = messageStatus.getStatusCode();
            String description = messageStatus.getDescription();
            String timestamp = Utils.DATE_TO_YYYY_MM_DD_HH_MM_SS(messageStatus.getTimestamp());
            StaffInfo staffInfo = messageStatus.getStaffInfo();
            if (responseFor == null ||
                    from == null ||
                    statuscode == null ||
                    description == null ||
                    timestamp == null ||
                    staffInfo == null) {
                return null;
            }
            String responsefor_organid = responseFor.getOrganId();
            String responsefor_code = responseFor.getCode();
            String responsefor_promulgationdate = Utils.DATE_TO_YYYY_MM_DD_HH_MM_SS(responseFor.getPromulgationDate());
            String responsefor_documentid = responseFor.getDocumentId();
            JSONObject responseForJson = new JSONObject();
            responseForJson.put("organid", responsefor_organid);
            responseForJson.put("code", responsefor_code);
            responseForJson.put("promulgationdate", responsefor_promulgationdate);
            responseForJson.put("documentid", responsefor_documentid);
            String from_organid = from.getOrganId();
            String from_organizationincharge = from.getOrganizationInCharge();
            String from_organname = from.getOrganName();
            String from_organadd = from.getOrganAdd();
            String from_email = from.getEmail();
            String from_telephone = from.getTelephone();
            String from_fax = from.getFax();
            String from_website = from.getWebsite();
            JSONObject fromJson = new JSONObject();
            fromJson.put("organid", from_organid);
            fromJson.put("organizationincharge", from_organizationincharge);
            fromJson.put("organname", from_organname);
            fromJson.put("organadd", from_organadd);
            fromJson.put("email", from_email);
            fromJson.put("telephone", from_telephone);
            fromJson.put("fax", from_fax);
            fromJson.put("website", from_website);
            String staffinfo_department = staffInfo.getDepartment();
            String staffinfo_staff = staffInfo.getStaff();
            String staffinfo_mobile = staffInfo.getMobile();
            String staffinfo_email = staffInfo.getEmail();
            JSONObject staffInfoJson = new JSONObject();
            staffInfoJson.put("department", staffinfo_department);
            staffInfoJson.put("staff", staffinfo_staff);
            staffInfoJson.put("mobile", staffinfo_mobile);
            staffInfoJson.put("email", staffinfo_email);
            jsonObject.put("responsefor", responseForJson);
            jsonObject.put("from", fromJson);
            jsonObject.put("statuscode", statuscode);
            jsonObject.put("description", description);
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("staffInfo", staffInfoJson);

            JSONObject statusJson = new JSONObject();
            statusJson.put("status", jsonObject);
            JSONObject headerJson = new JSONObject();
            headerJson.put("header", statusJson);
            return headerJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject parseEdoc(InputStream inputStream) {
        try {
            JSONObject jsonObject = new JSONObject();
            Ed ed = EdXmlParser.getInstance().parse(inputStream);
            Header header = ed.getHeader();
            JSONObject headerJson = new JSONObject();

            MessageHeader messageHeader = (MessageHeader) header.getMessageHeader();
            JSONObject messageHeaderJson = new JSONObject();
            Organization from = messageHeader.getFrom();
            JSONObject fromJson = new JSONObject();
            String from_organid = from.getOrganId();
            String from_organizationincharge = from.getOrganizationInCharge();
            String from_organname = from.getOrganName();
            String from_organadd = from.getOrganAdd();
            String from_email = from.getEmail();
            String from_telephone = from.getTelephone();
            String from_fax = from.getFax();
            String from_website = from.getWebsite();
            fromJson.put("organid", from_organid);
            fromJson.put("organizationincharge", from_organizationincharge);
            fromJson.put("organname", from_organname);
            fromJson.put("organadd", from_organadd);
            fromJson.put("email", from_email);
            fromJson.put("telephone", from_telephone);
            fromJson.put("fax", from_fax);
            fromJson.put("website", from_website);
            messageHeaderJson.put("from", fromJson);
            List<Organization> toes = messageHeader.getToes();
            for (Organization to : toes) {
                JSONObject toJson = new JSONObject();
                String to_organid = to.getOrganId();
                String to_organizationincharge = to.getOrganizationInCharge();
                String to_organname = to.getOrganName();
                String to_organadd = to.getOrganAdd();
                String to_email = to.getEmail();
                String to_telephone = to.getTelephone();
                String to_fax = to.getFax();
                String to_website = to.getWebsite();
                toJson.put("organid", to_organid);
                toJson.put("organizationincharge", to_organizationincharge);
                toJson.put("organname", to_organname);
                toJson.put("organadd", to_organadd);
                toJson.put("email", to_email);
                toJson.put("telephone", to_telephone);
                toJson.put("fax", to_fax);
                toJson.put("website", to_website);
                messageHeaderJson.append("to", toJson);
            }
            Code code = messageHeader.getCode();
            JSONObject codeJson = new JSONObject();
            String code_codenumber = code.getCodeNumber();
            String code_codenotation = code.getCodeNotation();
            codeJson.put("codenumber", code_codenumber);
            codeJson.put("codenotation", code_codenotation);
            messageHeaderJson.put("code", codeJson);
            PromulgationInfo promulgationInfo = messageHeader.getPromulgationInfo();
            JSONObject promulgationInfoJson = new JSONObject();
            String promulgationinfo_promulgationdate = Utils
                    .DATE_TO_YYYY_MM_DD_HH_MM_SS(promulgationInfo.getPromulgationDate());
            String promulgationinfo_place = promulgationInfo.getPlace();
            promulgationInfoJson.put("promulgationdate", promulgationinfo_promulgationdate);
            promulgationInfoJson.put("place", promulgationinfo_place);
            messageHeaderJson.put("promulgationinfo", promulgationInfoJson);
            DocumentType documentType = messageHeader.getDocumentType();
            JSONObject documentTypeJson = new JSONObject();
            String documenttype_type = Integer.toString(documentType.getType());
            String documenttype_typename = documentType.getTypeName();
            documentTypeJson.put("type", documenttype_type);
            documentTypeJson.put("typename", documenttype_typename);
            messageHeaderJson.put("documenttype", documentTypeJson);
            String subject = messageHeader.getSubject();
            messageHeaderJson.put("subject", subject);
            String content = messageHeader.getContent();
            messageHeaderJson.put("content", content);
            SignerInfo signerInfo = messageHeader.getSignerInfo();
            JSONObject signerInfoJson = new JSONObject();
            String signerinfo_competence = signerInfo.getCompetence();
            String signerinfo_position = signerInfo.getPosition();
            String signerinfo_fullname = signerInfo.getFullName();
            signerInfoJson.put("competence", signerinfo_competence);
            signerInfoJson.put("position", signerinfo_position);
            signerInfoJson.put("fullname", signerinfo_fullname);
            messageHeaderJson.put("signerinfo", signerInfoJson);
            String dueDate = Utils.DATE_TO_YYYY_MM_DD_HH_MM_SS(messageHeader.getDueDate());
            messageHeaderJson.put("duedate", dueDate);
            List<String> toPlaces = messageHeader.getToPlaces();
            for (String toPlace : toPlaces) {
                messageHeaderJson.append("toplace", toPlace);
            }
            OtherInfo otherInfo = messageHeader.getOtherInfo();
            JSONObject otherInfoJson = new JSONObject();
            String otherInfo_priority = Integer.toString(otherInfo.getPriority());
            String otherInfo_sphereofpromulgation = otherInfo.getSphereOfPromulgation();
            String otherInfo_typernotation = otherInfo.getTyperNotation();
            String otherInfo_promulgationamount = Integer.toString(otherInfo.getPromulgationAmount());
            String otherInfo_pageamount = Integer.toString(otherInfo.getPageAmount());
            List<String> otherInfo_appendixes = otherInfo.getAppendixes();
            otherInfoJson.put("priority", otherInfo_priority);
            otherInfoJson.put("sphereofpromulgation", otherInfo_sphereofpromulgation);
            otherInfoJson.put("typernotation", otherInfo_typernotation);
            otherInfoJson.put("promulgationamount", otherInfo_promulgationamount);
            otherInfoJson.put("pageamount", otherInfo_pageamount);
            for (String appendix : otherInfo_appendixes) {
                otherInfoJson.append("appendixes", appendix);
            }
            List<ResponseFor> responseFor = messageHeader.getResponseFor();
            for (ResponseFor responsefor : responseFor) {
                JSONObject responseForJson = new JSONObject();
                String responsefor_organid = responsefor.getOrganId();
                String responsefor_code = responsefor.getCode();
                String responsefor_documentid = responsefor.getDocumentId();
                String responsefor_promulgationdate = Utils
                        .DATE_TO_YYYY_MM_DD_HH_MM_SS(responsefor.getPromulgationDate());
                responseForJson.put("organid", responsefor_organid);
                responseForJson.put("code", responsefor_code);
                responseForJson.put("documentid", responsefor_documentid);
                responseForJson.put("promulgationdate", responsefor_promulgationdate);
                messageHeaderJson.append("responsefor", responseForJson);
            }
            Integer steeringType = messageHeader.getSteeringType();
            messageHeaderJson.put("steeringtype", steeringType);
            String documentId = messageHeader.getDocumentId();
            messageHeaderJson.put("documentid", documentId);

            headerJson.put("messageheader", messageHeaderJson);

            TraceHeaderList traceHeaderList = header.getTraceHeaderList();
            JSONObject traceHeaderListJson = new JSONObject();
            List<TraceHeader> traceHeaders = traceHeaderList.getTraceHeaders();
            for (TraceHeader traceheader : traceHeaders) {
                JSONObject traceHeaderJson = new JSONObject();
                String traceheader_organid = traceheader.getOrganId();
                String traceheader_timestamp = Utils.DATE_TO_YYYY_MM_DD_HH_MM_SS(traceheader.getTimestamp());
                traceHeaderJson.put("organid", traceheader_organid);
                traceHeaderJson.put("timestamp", traceheader_timestamp);
                traceHeaderListJson.append("traceheader", traceHeaderJson);
            }
            Bussiness bussiness = traceHeaderList.getBussiness();
            JSONObject bussinessJson = new JSONObject();
            String bussiness_bussinessdoctype = bussiness.getBussinessDocType();
            bussinessJson.put("bussinessdoctype", bussiness_bussinessdoctype);
            String bussiness_bussinessdocreason = bussiness.getBussinessDocReason();
            bussinessJson.put("bussinessdocreason", bussiness_bussinessdocreason);
            List<BussinessDocumentInfo> bussinessDocumentInfos = bussiness.getBussinessDocumentInfo();
            for (BussinessDocumentInfo bussinessDocumentInfo : bussinessDocumentInfos) {
                JSONObject bussinessDocumentInfoJson = new JSONObject();
                String bussinessdocumentinfo_documentinfo = bussinessDocumentInfo.getdocumentInfo();
                String bussinessdocumentinfo_documentreceiver = bussinessDocumentInfo.getdocumentReceiver();
                bussinessDocumentInfoJson.put("documentinfo", bussinessdocumentinfo_documentinfo);
                bussinessDocumentInfoJson.put("documentreceiver", bussinessdocumentinfo_documentreceiver);
                List<ReceiverList> receiverLists = bussinessDocumentInfo.getreceiverList();
                for (ReceiverList receiverList : receiverLists) {
                    List<Receiver> receivers = receiverList.getReceiver();
                    JSONObject receiverListJson = new JSONObject();
                    for (Receiver receiver : receivers) {
                        JSONObject receiverJson = new JSONObject();
                        String receiver_receivertype = receiver.getReceiverType();
                        String receiver_organid = receiver.getOrganId();
                        receiverJson.put("receivertype", receiver_receivertype);
                        receiverJson.put("organid", receiver_organid);
                        receiverListJson.append("receiver", receiverJson);
                    }
                    bussinessDocumentInfoJson.append("receiverlist", receiverListJson);
                }
                bussinessJson.append("bussinessdocumentinfo", bussinessDocumentInfoJson);
            }
            String bussiness_documentid = bussiness.getDocumentId();
            bussinessJson.put("documentid", bussiness_documentid);
            List<StaffInfo> staffInfos = bussiness.getStaffInfo();
            for (StaffInfo staffInfo : staffInfos) {
                JSONObject staffInfoJson = new JSONObject();
                String staffinfo_department = staffInfo.getDepartment();
                String staffinfo_staff = staffInfo.getStaff();
                String staffinfo_mobile = staffInfo.getMobile();
                String staffinfo_email = staffInfo.getEmail();
                staffInfoJson.put("department", staffinfo_department);
                staffInfoJson.put("staff", staffinfo_staff);
                staffInfoJson.put("mobile", staffinfo_mobile);
                staffInfoJson.put("email", staffinfo_email);
                bussinessJson.append("staffinfo", staffInfoJson);
            }
            String bussiness_paper = bussiness.getPaper();
            bussinessJson.put("paper", bussiness_paper);
            List<ReplacementInfoList> replacementInfoLists = bussiness.getReplacementInfoList();
            for (ReplacementInfoList replacementInfoList : replacementInfoLists) {
                List<ReplacementInfo> replacementInfos = replacementInfoList.getReplacementInfo();
                JSONArray replacementInfoArray = new JSONArray();
                for (ReplacementInfo replacementInfo : replacementInfos) {
                    JSONObject replacementInfoJson = new JSONObject();
                    String replacementinfo_documentid = replacementInfo.getDocumentId();
                    OrganIdList replacementinfo_organidlist = replacementInfo.getOrganIdList();
                    List<String> replacementinfo_organids = replacementinfo_organidlist.getOrganId();
                    replacementInfoJson.put("documentid", replacementinfo_documentid);
                    replacementInfoJson.put("organidlist", replacementinfo_organids);
                    replacementInfoArray.put(replacementInfoJson);
                }
                bussinessJson.append("replacementinfolist", replacementInfoArray);
            }
            traceHeaderListJson.put("bussiness", bussinessJson);

            headerJson.put("traceheaderlist", traceHeaderListJson);

            Signature signature = header.getSignature();
            JSONObject signatureJson = new JSONObject();
            SignedInfo signedInfo = signature.getSignedInfo();
            JSONObject signedInfoJson = new JSONObject();
            String signedinfo_canonicalizationmethod = signedInfo.getCanonicalizationMethod();
            String signedinfo_signaturemethod = signedInfo.getSignatureMethod();
            signedInfoJson.put("canonicalizationmethod", signedinfo_canonicalizationmethod);
            signedInfoJson.put("signaturemethod", signedinfo_signaturemethod);
            List<SignReference> signedinfo_references = signedInfo.getReference();
            for (SignReference signReference : signedinfo_references) {
                JSONObject signReferenceJson = new JSONObject();
                String signreference_uri = signReference.getURI();
                String signreference_digestmethod = signReference.getDigestMethod();
                String signreference_digestvalue = signReference.getDigestValue();
                signReferenceJson.put("uri", signreference_uri);
                signReferenceJson.put("digestmethod", signreference_digestmethod);
                signReferenceJson.put("digestvalue", signreference_digestvalue);
                signedInfoJson.append("reference", signReferenceJson);
            }
            signatureJson.put("signedinfo", signedInfoJson);
            String signatureValue = signature.getSignatureValue();
            signatureJson.put("signaturevalue", signatureValue);
            KeyInfo keyInfo = signature.getKeyInfo();
            JSONObject keyInfoJson = new JSONObject();
            X509Data x509Data = keyInfo.getX509Data();
            JSONObject x509DataJson = new JSONObject();
            String x509data_x509certificate = x509Data.getX509Certificate();
            String x509data_x509subjectname = x509Data.getX509SubjectName();
            x509DataJson.put("x509certificate", x509data_x509certificate);
            x509DataJson.put("x509subjectname", x509data_x509subjectname);
            keyInfoJson.put("x509data", x509DataJson);
            signatureJson.put("keyinfo", keyInfoJson);

            headerJson.put("signature", signatureJson);

            jsonObject.put("header", headerJson);

            Body body = ed.getBody();
            JSONObject bodyJson = new JSONObject();
            Manifest manifest = body.getManifest();
            JSONObject manifestJson = new JSONObject();
            String version = manifest.getVersion();
            manifestJson.put("version", version);
            List<Reference> references = manifest.getReferences();
            for (Reference reference : references) {
                JSONObject referenceJson = new JSONObject();
                String reference_contentid = reference.getContentId();
                String reference_contenttype = reference.getContentType();
                String reference_description = reference.getDescription();
                String reference_attachmentname = reference.getAttachmentName();
                referenceJson.put("contentid", reference_contentid);
                referenceJson.put("contenttype", reference_contenttype);
                referenceJson.put("description", reference_description);
                referenceJson.put("attachmentname", reference_attachmentname);
                manifestJson.append("reference", referenceJson);
            }
            bodyJson.put("manifest", manifestJson);
            jsonObject.put("body", bodyJson);

            List<Attachment> attachments = ed.getAttachments();
            JSONArray attachmentsArray = new JSONArray();
            for (Attachment attachment : attachments) {
                JSONObject attachmentJson = new JSONObject();
                String attachment_contentid = attachment.getContentId();
                String attachment_contenttype = attachment.getContentType();
                String attachment_description = attachment.getDescription();
                String attachment_fieldname = attachment.getFieldName();
                String attachment_format = attachment.getFormat();
                String attachment_mimetype = attachment.getMimeType();
                String attachment_name = attachment.getName();
                String attachment_content_base64 = Utils.ENCODE_TO_BASE64(attachment.getContent());
                attachmentJson.put("contentid", attachment_contentid);
                attachmentJson.put("contenttype", attachment_contenttype);
                attachmentJson.put("description", attachment_description);
                attachmentJson.put("fieldname", attachment_fieldname);
                attachmentJson.put("format", attachment_format);
                attachmentJson.put("mimetype", attachment_mimetype);
                attachmentJson.put("name", attachment_name);
                attachmentJson.put("content", attachment_content_base64);
                attachmentsArray.put(attachmentJson);
            }
            jsonObject.put("attachments", attachmentsArray);

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
