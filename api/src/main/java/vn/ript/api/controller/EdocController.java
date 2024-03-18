package vn.ript.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vnpt.xml.base.Content;

import vn.ript.api.utils.CustomResponse;
import vn.ript.api.utils.EdXMLBuild;
import vn.ript.api.utils.EdXMLParser;
import vn.ript.api.utils.Utils;

@RestController
@RequestMapping("api/v1/edocs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EdocController {
    @PostMapping("/send")
    public ResponseEntity<Object> sendEdoc(
            @RequestPart(name = "files", required = true) List<MultipartFile> multipartFiles,
            @RequestPart(name = "metadata", required = true) String metadata) {
        try {
            JSONObject jsonObject = new JSONObject(metadata);

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
            JSONObject attachments_metadata = null;
            List<File> attachments = new ArrayList<File>();
            if (jsonObject.has("header_messageheader_from_organid")) {
                header_messageheader_from_organid = (String) jsonObject.getString("header_messageheader_from_organid");
            }
            if (jsonObject.has("header_messageheader_from_organizationincharge")) {
                header_messageheader_from_organizationincharge = (String) jsonObject
                        .getString("header_messageheader_from_organizationincharge");
            }
            if (jsonObject.has("header_messageheader_from_organname")) {
                header_messageheader_from_organname = (String) jsonObject
                        .getString("header_messageheader_from_organname");
            }
            if (jsonObject.has("header_messageheader_from_organadd")) {
                header_messageheader_from_organadd = (String) jsonObject
                        .getString("header_messageheader_from_organadd");
            }
            if (jsonObject.has("header_messageheader_from_email")) {
                header_messageheader_from_email = (String) jsonObject.getString("header_messageheader_from_email");
            }
            if (jsonObject.has("header_messageheader_from_telephone")) {
                header_messageheader_from_telephone = (String) jsonObject
                        .getString("header_messageheader_from_telephone");
            }
            if (jsonObject.has("header_messageheader_from_fax")) {
                header_messageheader_from_fax = (String) jsonObject.getString("header_messageheader_from_fax");
            }
            if (jsonObject.has("header_messageheader_from_website")) {
                header_messageheader_from_website = (String) jsonObject.getString("header_messageheader_from_website");
            }
            if (jsonObject.has("header_messageheader_toes")) {
                header_messageheader_toes = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_messageheader_toes");
                for (Object obj : jsonArray) {
                    header_messageheader_toes.add((JSONObject) obj);
                }
            }
            if (jsonObject.has("header_messageheader_code_codenumber")) {
                header_messageheader_code_codenumber = (String) jsonObject
                        .getString("header_messageheader_code_codenumber");
            }
            if (jsonObject.has("header_messageheader_code_codenotation")) {
                header_messageheader_code_codenotation = (String) jsonObject
                        .getString("header_messageheader_code_codenotation");
            }
            if (jsonObject.has("header_messageheader_promulgationinfo_place")) {
                header_messageheader_promulgationinfo_place = (String) jsonObject
                        .getString("header_messageheader_promulgationinfo_place");
            }
            if (jsonObject.has("header_messageheader_promulgationinfo_promulgationdate")) {
                header_messageheader_promulgationinfo_promulgationdate = (String) jsonObject
                        .getString("header_messageheader_promulgationinfo_promulgationdate");
            }
            if (jsonObject.has("header_messageheader_documenttype_type")) {
                header_messageheader_documenttype_type = (String) jsonObject
                        .getString("header_messageheader_documenttype_type");
            }
            if (jsonObject.has("header_messageheader_documenttype_typename")) {
                header_messageheader_documenttype_typename = (String) jsonObject
                        .getString("header_messageheader_documenttype_typename");
            }
            if (jsonObject.has("header_messageheader_subject")) {
                header_messageheader_subject = (String) jsonObject.getString("header_messageheader_subject");
            }
            if (jsonObject.has("header_messageheader_content")) {
                header_messageheader_content = (String) jsonObject.getString("header_messageheader_content");
            }
            if (jsonObject.has("header_messageheader_signerinfo_competence")) {
                header_messageheader_signerinfo_competence = (String) jsonObject
                        .getString("header_messageheader_signerinfo_competence");
            }
            if (jsonObject.has("header_messageheader_signerinfo_position")) {
                header_messageheader_signerinfo_position = (String) jsonObject
                        .getString("header_messageheader_signerinfo_position");
            }
            if (jsonObject.has("header_messageheader_signerinfo_fullname")) {
                header_messageheader_signerinfo_fullname = (String) jsonObject
                        .getString("header_messageheader_signerinfo_fullname");
            }
            if (jsonObject.has("header_messageheader_duedate")) {
                header_messageheader_duedate = (String) jsonObject.getString("header_messageheader_duedate");
            }
            if (jsonObject.has("header_messageheader_toplaces")) {
                header_messageheader_toplaces = new ArrayList<String>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_messageheader_toplaces");
                for (Object obj : jsonArray) {
                    header_messageheader_toplaces.add((String) obj);
                }
            }
            if (jsonObject.has("header_messageheader_otherinfo_priority")) {
                header_messageheader_otherinfo_priority = (String) jsonObject
                        .getString("header_messageheader_otherinfo_priority");
            }
            if (jsonObject.has("header_messageheader_otherinfo_sphereofpromulgation")) {
                header_messageheader_otherinfo_sphereofpromulgation = (String) jsonObject
                        .getString("header_messageheader_otherinfo_sphereofpromulgation");
            }
            if (jsonObject.has("header_messageheader_otherinfo_typernotation")) {
                header_messageheader_otherinfo_typernotation = (String) jsonObject
                        .getString("header_messageheader_otherinfo_typernotation");
            }
            if (jsonObject.has("header_messageheader_otherinfo_promulgationamount")) {
                header_messageheader_otherinfo_promulgationamount = (String) jsonObject
                        .getString("header_messageheader_otherinfo_promulgationamount");
            }
            if (jsonObject.has("header_messageheader_otherinfo_pageamount")) {
                header_messageheader_otherinfo_pageamount = (String) jsonObject
                        .getString("header_messageheader_otherinfo_pageamount");
            }
            if (jsonObject.has("header_messageheader_otherinfo_appendixes")) {
                header_messageheader_otherinfo_appendixes = new ArrayList<String>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_messageheader_otherinfo_appendixes");
                for (Object o : jsonArray) {
                    header_messageheader_otherinfo_appendixes.add((String) o);
                }
            }
            if (jsonObject.has("header_messageheader_responsefors")) {
                header_messageheader_responsefors = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_messageheader_responsefors");
                for (Object o : jsonArray) {
                    header_messageheader_responsefors.add((JSONObject) o);
                }
            }
            if (jsonObject.has("header_messageheader_steeringtype")) {
                header_messageheader_steeringtype = (String) jsonObject.getString("header_messageheader_steeringtype");
            }
            if (jsonObject.has("header_messageheader_documentid")) {
                header_messageheader_documentid = (String) jsonObject.getString("header_messageheader_documentid");
            }
            if (jsonObject.has("header_traceheaderlist_traceheader")) {
                header_traceheaderlist_traceheader = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_traceheaderlist_traceheader");
                for (Object o : jsonArray) {
                    header_traceheaderlist_traceheader.add((JSONObject) o);
                }
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_bussinessdoctype")) {
                header_traceheaderlist_bussiness_bussinessdoctype = (String) jsonObject
                        .getString("header_traceheaderlist_bussiness_bussinessdoctype");
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_bussinessdocreason")) {
                header_traceheaderlist_bussiness_bussinessdocreason = (String) jsonObject
                        .getString("header_traceheaderlist_bussiness_bussinessdocreason");
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_bussinessdocumentinfo")) {
                header_traceheaderlist_bussiness_bussinessdocumentinfo = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_traceheaderlist_bussiness_bussinessdocumentinfo");
                for (Object o : jsonArray) {
                    header_traceheaderlist_bussiness_bussinessdocumentinfo.add((JSONObject) o);
                }
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_documentid")) {
                header_traceheaderlist_bussiness_documentid = (String) jsonObject
                        .getString("header_traceheaderlist_bussiness_documentid");
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_staffinfoes")) {
                header_traceheaderlist_bussiness_staffinfoes = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_traceheaderlist_bussiness_staffinfoes");
                for (Object o : jsonArray) {
                    header_traceheaderlist_bussiness_staffinfoes.add((JSONObject) o);
                }
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_paper")) {
                header_traceheaderlist_bussiness_paper = (String) jsonObject
                        .getString("header_traceheaderlist_bussiness_paper");
            }
            if (jsonObject.has("header_traceheaderlist_bussiness_replacementinfolist_replacementinfo")) {
                header_traceheaderlist_bussiness_replacementinfolist_replacementinfo = new ArrayList<JSONArray>();
                JSONArray jsonArray = jsonObject
                        .getJSONArray("header_traceheaderlist_bussiness_replacementinfolist_replacementinfo");
                for (Object o : jsonArray) {
                    JSONArray jsonArray_tmp = (JSONArray) o;
                    header_traceheaderlist_bussiness_replacementinfolist_replacementinfo.add(jsonArray_tmp);
                }
            }
            if (jsonObject.has("header_signature_signatureinfo_canonicalizationmethod")) {
                header_signature_signatureinfo_canonicalizationmethod = (String) jsonObject
                        .getString("header_signature_signatureinfo_canonicalizationmethod");
            }
            if (jsonObject.has("header_signature_signatureinfo_signaturemethod")) {
                header_signature_signatureinfo_signaturemethod = (String) jsonObject
                        .getString("header_signature_signatureinfo_signaturemethod");
            }
            if (jsonObject.has("header_signature_signatureinfo_references")) {
                header_signature_signatureinfo_references = new ArrayList<JSONObject>();
                JSONArray jsonArray = jsonObject.getJSONArray("header_signature_signatureinfo_references");
                for (Object o : jsonArray) {
                    header_signature_signatureinfo_references.add((JSONObject) o);
                }
            }
            if (jsonObject.has("header_signature_signaturevalue")) {
                header_signature_signaturevalue = (String) jsonObject.getString("header_signature_signaturevalue");
            }
            if (jsonObject.has("header_signature_keyinfo_x509data_x509subjectname")) {
                header_signature_keyinfo_x509data_x509subjectname = (String) jsonObject
                        .getString("header_signature_keyinfo_x509data_x509subjectname");
            }
            if (jsonObject.has("header_signature_keyinfo_x509data_x509certificate")) {
                header_signature_keyinfo_x509data_x509certificate = (String) jsonObject
                        .getString("header_signature_keyinfo_x509data_x509certificate");
            }
            if (jsonObject.has("attachments_metadata")) {
                attachments_metadata = jsonObject.getJSONObject("attachments_metadata");
            }
            for (MultipartFile multipartFile : multipartFiles) {
                File file = Utils.MULTIPART_FILE_TO_FILE(multipartFile);
                attachments.add(file);
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
                    attachments_metadata,
                    attachments);
            File file = edoc.getContent();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            CustomResponse<InputStreamResource> response = new CustomResponse<InputStreamResource>(200, resource);
            String filename = file.getName();
            file.delete();
            for (File f : attachments) {
                f.delete();
            }
            return response.response_file(filename);
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

}
