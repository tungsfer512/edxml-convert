package vn.ript.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vnpt.xml.base.Content;

import vn.ript.api.utils.CustomResponse;
import vn.ript.api.utils.EdXMLBuild;
import vn.ript.api.utils.EdXMLParser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/edocs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EdocController {
    @PostMapping("/send")
    public ResponseEntity<Object> sendEdoc(@RequestBody Map<String, Object> entity) {
        try {
            String header_messageheader_from_organid = null;
            String header_messageheader_from_organizationincharge = null;
            String header_messageheader_from_organname = null;
            String header_messageheader_from_organadd = null;
            String header_messageheader_from_email = null;
            String header_messageheader_from_telephone = null;
            String header_messageheader_from_fax = null;
            String header_messageheader_from_website = null;
            List<JSONObject> header_messageheader_toes = null;
            String header_messageheader_code_codenumber = null;
            String header_messageheader_code_codenotation = null;
            String header_messageheader_promulgationinfo_place = null;
            String header_messageheader_promulgationinfo_promulgationdate = null;
            String header_messageheader_documenttype_type = null;
            String header_messageheader_documenttype_typename = null;
            String header_messageheader_subject = null;
            String header_messageheader_content = null;
            String header_messageheader_signerinfo_competence = null;
            String header_messageheader_signerinfo_position = null;
            String header_messageheader_signerinfo_fullname = null;
            String header_messageheader_duedate = null;
            List<String> header_messageheader_toplaces = null;
            String header_messageheader_otherinfo_priority = null;
            String header_messageheader_otherinfo_sphereofpromulgation = null;
            String header_messageheader_otherinfo_typernotation = null;
            String header_messageheader_otherinfo_promulgationamount = null;
            String header_messageheader_otherinfo_pageamount = null;
            List<String> header_messageheader_otherinfo_appendixes = null;
            List<JSONObject> header_messageheader_responsefors = null;
            String header_messageheader_steeringtype = null;
            String header_messageheader_documentid = null;
            List<JSONObject> header_traceheaderlist_traceheader = null;
            String header_traceheaderlist_bussiness_bussinessdoctype = null;
            String header_traceheaderlist_bussiness_bussinessdocreason = null;
            List<JSONObject> header_traceheaderlist_bussiness_bussinessdocumentinfo = null;
            String header_traceheaderlist_bussiness_documentid = null;
            List<JSONObject> header_traceheaderlist_bussiness_staffinfoes = null;
            String header_traceheaderlist_bussiness_paper = null;
            List<JSONArray> header_traceheaderlist_bussiness_replacementinfolist_replacementinfo = null;
            String header_signature_signatureinfo_canonicalizationmethod = null;
            String header_signature_signatureinfo_signaturemethod = null;
            List<JSONObject> header_signature_signatureinfo_references = null;
            String header_signature_signaturevalue = null;
            String header_signature_keyinfo_x509data_x509subjectname = null;
            String header_signature_keyinfo_x509data_x509certificate = null;
            List<JSONObject> attachments = null;
            if (entity.containsKey("header_messageheader_from_organid")) {
                header_messageheader_from_organid = (String) entity.get("header_messageheader_from_organid");
            }
            if (entity.containsKey("header_messageheader_from_organizationincharge")) {
                header_messageheader_from_organizationincharge = (String) entity
                        .get("header_messageheader_from_organizationincharge");
            }
            if (entity.containsKey("header_messageheader_from_organname")) {
                header_messageheader_from_organname = (String) entity.get("header_messageheader_from_organname");
            }
            if (entity.containsKey("header_messageheader_from_organadd")) {
                header_messageheader_from_organadd = (String) entity.get("header_messageheader_from_organadd");
            }
            if (entity.containsKey("header_messageheader_from_email")) {
                header_messageheader_from_email = (String) entity.get("header_messageheader_from_email");
            }
            if (entity.containsKey("header_messageheader_from_telephone")) {
                header_messageheader_from_telephone = (String) entity.get("header_messageheader_from_telephone");
            }
            if (entity.containsKey("header_messageheader_from_fax")) {
                header_messageheader_from_fax = (String) entity.get("header_messageheader_from_fax");
            }
            if (entity.containsKey("header_messageheader_from_website")) {
                header_messageheader_from_website = (String) entity.get("header_messageheader_from_website");
            }
            if (entity.containsKey("header_messageheader_toes")) {
                header_messageheader_toes = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_messageheader_toes");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_messageheader_toes.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_messageheader_code_codenumber")) {
                header_messageheader_code_codenumber = (String) entity.get("header_messageheader_code_codenumber");
            }
            if (entity.containsKey("header_messageheader_code_codenotation")) {
                header_messageheader_code_codenotation = (String) entity.get("header_messageheader_code_codenotation");
            }
            if (entity.containsKey("header_messageheader_promulgationinfo_place")) {
                header_messageheader_promulgationinfo_place = (String) entity
                        .get("header_messageheader_promulgationinfo_place");
            }
            if (entity.containsKey("header_messageheader_promulgationinfo_promulgationdate")) {
                header_messageheader_promulgationinfo_promulgationdate = (String) entity
                        .get("header_messageheader_promulgationinfo_promulgationdate");
            }
            if (entity.containsKey("header_messageheader_documenttype_type")) {
                header_messageheader_documenttype_type = (String) entity.get("header_messageheader_documenttype_type");
            }
            if (entity.containsKey("header_messageheader_documenttype_typename")) {
                header_messageheader_documenttype_typename = (String) entity
                        .get("header_messageheader_documenttype_typename");
            }
            if (entity.containsKey("header_messageheader_subject")) {
                header_messageheader_subject = (String) entity.get("header_messageheader_subject");
            }
            if (entity.containsKey("header_messageheader_content")) {
                header_messageheader_content = (String) entity.get("header_messageheader_content");
            }
            if (entity.containsKey("header_messageheader_signerinfo_competence")) {
                header_messageheader_signerinfo_competence = (String) entity
                        .get("header_messageheader_signerinfo_competence");
            }
            if (entity.containsKey("header_messageheader_signerinfo_position")) {
                header_messageheader_signerinfo_position = (String) entity
                        .get("header_messageheader_signerinfo_position");
            }
            if (entity.containsKey("header_messageheader_signerinfo_fullname")) {
                header_messageheader_signerinfo_fullname = (String) entity
                        .get("header_messageheader_signerinfo_fullname");
            }
            if (entity.containsKey("header_messageheader_duedate")) {
                header_messageheader_duedate = (String) entity.get("header_messageheader_duedate");
            }
            if (entity.containsKey("header_messageheader_toplaces")) {
                header_messageheader_toplaces = new ArrayList<String>();
                List<Object> obj = (List<Object>) entity.get("header_messageheader_toplaces");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_messageheader_toplaces.add((String) o);
                }
            }
            if (entity.containsKey("header_messageheader_otherinfo_priority")) {
                header_messageheader_otherinfo_priority = (String) entity
                        .get("header_messageheader_otherinfo_priority");
            }
            if (entity.containsKey("header_messageheader_otherinfo_sphereofpromulgation")) {
                header_messageheader_otherinfo_sphereofpromulgation = (String) entity
                        .get("header_messageheader_otherinfo_sphereofpromulgation");
            }
            if (entity.containsKey("header_messageheader_otherinfo_typernotation")) {
                header_messageheader_otherinfo_typernotation = (String) entity
                        .get("header_messageheader_otherinfo_typernotation");
            }
            if (entity.containsKey("header_messageheader_otherinfo_promulgationamount")) {
                header_messageheader_otherinfo_promulgationamount = (String) entity
                        .get("header_messageheader_otherinfo_promulgationamount");
            }
            if (entity.containsKey("header_messageheader_otherinfo_pageamount")) {
                header_messageheader_otherinfo_pageamount = (String) entity
                        .get("header_messageheader_otherinfo_pageamount");
            }
            if (entity.containsKey("header_messageheader_otherinfo_appendixes")) {
                header_messageheader_otherinfo_appendixes = new ArrayList<String>();
                List<Object> obj = (List<Object>) entity.get("header_messageheader_otherinfo_appendixes");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_messageheader_otherinfo_appendixes.add((String) o);
                }
            }
            if (entity.containsKey("header_messageheader_responsefors")) {
                header_messageheader_responsefors = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_messageheader_responsefors");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_messageheader_responsefors.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_messageheader_steeringtype")) {
                header_messageheader_steeringtype = (String) entity.get("header_messageheader_steeringtype");
            }
            if (entity.containsKey("header_messageheader_documentid")) {
                header_messageheader_documentid = (String) entity.get("header_messageheader_documentid");
            }
            if (entity.containsKey("header_traceheaderlist_traceheader")) {
                header_traceheaderlist_traceheader = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_traceheaderlist_traceheader");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_traceheaderlist_traceheader.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_bussinessdoctype")) {
                header_traceheaderlist_bussiness_bussinessdoctype = (String) entity
                        .get("header_traceheaderlist_bussiness_bussinessdoctype");
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_bussinessdocreason")) {
                header_traceheaderlist_bussiness_bussinessdocreason = (String) entity
                        .get("header_traceheaderlist_bussiness_bussinessdocreason");
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_bussinessdocumentinfo")) {
                header_traceheaderlist_bussiness_bussinessdocumentinfo = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_traceheaderlist_bussiness_bussinessdocumentinfo");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_traceheaderlist_bussiness_bussinessdocumentinfo.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_documentid")) {
                header_traceheaderlist_bussiness_documentid = (String) entity
                        .get("header_traceheaderlist_bussiness_documentid");
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_staffinfoes")) {
                header_traceheaderlist_bussiness_staffinfoes = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_traceheaderlist_bussiness_staffinfoes");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_traceheaderlist_bussiness_staffinfoes.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_paper")) {
                header_traceheaderlist_bussiness_paper = (String) entity.get("header_traceheaderlist_bussiness_paper");
            }
            if (entity.containsKey("header_traceheaderlist_bussiness_replacementinfolist_replacementinfo")) {
                header_traceheaderlist_bussiness_replacementinfolist_replacementinfo = new ArrayList<JSONArray>();
                List<Object> obj = (List<Object>) entity
                        .get("header_traceheaderlist_bussiness_replacementinfolist_replacementinfo");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    JSONArray jsonArray_tmp = (JSONArray) o;
                    header_traceheaderlist_bussiness_replacementinfolist_replacementinfo.add(jsonArray_tmp);
                }
            }
            if (entity.containsKey("header_signature_signatureinfo_canonicalizationmethod")) {
                header_signature_signatureinfo_canonicalizationmethod = (String) entity
                        .get("header_signature_signatureinfo_canonicalizationmethod");
            }
            if (entity.containsKey("header_signature_signatureinfo_signaturemethod")) {
                header_signature_signatureinfo_signaturemethod = (String) entity
                        .get("header_signature_signatureinfo_signaturemethod");
            }
            if (entity.containsKey("header_signature_signatureinfo_references")) {
                header_signature_signatureinfo_references = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("header_signature_signatureinfo_references");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    header_signature_signatureinfo_references.add((JSONObject) o);
                }
            }
            if (entity.containsKey("header_signature_signaturevalue")) {
                header_signature_signaturevalue = (String) entity.get("header_signature_signaturevalue");
            }
            if (entity.containsKey("header_signature_keyinfo_x509data_x509subjectname")) {
                header_signature_keyinfo_x509data_x509subjectname = (String) entity
                        .get("header_signature_keyinfo_x509data_x509subjectname");
            }
            if (entity.containsKey("header_signature_keyinfo_x509data_x509certificate")) {
                header_signature_keyinfo_x509data_x509certificate = (String) entity
                        .get("header_signature_keyinfo_x509data_x509certificate");
            }
            if (entity.containsKey("attachments")) {
                attachments = new ArrayList<JSONObject>();
                List<Object> obj = (List<Object>) entity.get("attachments");
                JSONArray jsonArray = new JSONArray(obj);
                for (Object o : jsonArray) {
                    attachments.add((JSONObject) o);
                }
            }
            System.out.println("--------------------Start create_edoc_new--------------------");
            Content edoc = EdXMLBuild.create_edoc_new(
                    header_messageheader_from_organid,
                    header_messageheader_from_organizationincharge,
                    header_messageheader_from_organname,
                    header_messageheader_from_organadd,
                    header_messageheader_from_email,
                    header_messageheader_from_telephone,
                    header_messageheader_from_fax,
                    header_messageheader_from_website,
                    header_messageheader_toes,
                    header_messageheader_code_codenumber,
                    header_messageheader_code_codenotation,
                    header_messageheader_promulgationinfo_place,
                    header_messageheader_promulgationinfo_promulgationdate,
                    header_messageheader_documenttype_type,
                    header_messageheader_documenttype_typename,
                    header_messageheader_subject,
                    header_messageheader_content,
                    header_messageheader_signerinfo_competence,
                    header_messageheader_signerinfo_position,
                    header_messageheader_signerinfo_fullname,
                    header_messageheader_duedate,
                    header_messageheader_toplaces,
                    header_messageheader_otherinfo_priority,
                    header_messageheader_otherinfo_sphereofpromulgation,
                    header_messageheader_otherinfo_typernotation,
                    header_messageheader_otherinfo_promulgationamount,
                    header_messageheader_otherinfo_pageamount,
                    header_messageheader_otherinfo_appendixes,
                    header_messageheader_responsefors,
                    header_messageheader_steeringtype,
                    header_messageheader_documentid,
                    header_traceheaderlist_traceheader,
                    header_traceheaderlist_bussiness_bussinessdoctype,
                    header_traceheaderlist_bussiness_bussinessdocreason,
                    header_traceheaderlist_bussiness_bussinessdocumentinfo,
                    header_traceheaderlist_bussiness_documentid,
                    header_traceheaderlist_bussiness_staffinfoes,
                    header_traceheaderlist_bussiness_paper,
                    header_traceheaderlist_bussiness_replacementinfolist_replacementinfo,
                    header_signature_signatureinfo_canonicalizationmethod,
                    header_signature_signatureinfo_signaturemethod,
                    header_signature_signatureinfo_references,
                    header_signature_signaturevalue,
                    header_signature_keyinfo_x509data_x509subjectname,
                    header_signature_keyinfo_x509data_x509certificate,
                    attachments);
            File file = edoc.getContent();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            CustomResponse<InputStreamResource> response = new CustomResponse<InputStreamResource>(200, resource);
            return response.response_file(file.getName());
        } catch (Exception e) {
            CustomResponse<Object> response = new CustomResponse<>(500, e);
            return response.response();
        }
    }

    @PostMapping("/parse")
    public ResponseEntity<Object> parseEdoc(
            @RequestPart(name = "file", required = true) MultipartFile multipartFile) {
        try {
            InputStream fileInputStream = multipartFile.getInputStream();
            JSONObject jsonObject = EdXMLParser.parseEdoc(fileInputStream);
            CustomResponse<Object> response = new CustomResponse<>(200, jsonObject.toString());
            return response.response();
        } catch (Exception e) {
            CustomResponse<Object> response = new CustomResponse<>(500, e);
            return response.response();
        }
    }

    @PostMapping("/attachments/{attachment_name}")
    public ResponseEntity<Object> parseEdoc(
            @PathVariable(name = "attachment_name", required = true) String attachment_name,
            @RequestPart(name = "file", required = true) MultipartFile multipartFile) {
        try {
            InputStreamResource inputStreamResource = EdXMLParser.getAttachmentFromEdoc(multipartFile.getInputStream(),
                    attachment_name);
            CustomResponse<Object> response = new CustomResponse<>(200, inputStreamResource);
            return response.response_file(attachment_name);
        } catch (Exception e) {
            CustomResponse<Object> response = new CustomResponse<>(500, e);
            return response.response();
        }
    }

}
