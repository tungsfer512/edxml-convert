package vn.ript.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("api/v1/status")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusController {
    @PostMapping("/send")
    public ResponseEntity<Object> sendStatus(@RequestBody Map<String, Object> entity) {
        try {
            String responsefor_organid = "";
            String responsefor_code = "";
            String responsefor_promulgationdate = "";
            String responsefor_documentid = "";
            String from_organid = "";
            String from_organizationincharge = "";
            String from_organname = "";
            String from_organadd = "";
            String from_email = "";
            String from_telephone = "";
            String from_fax = "";
            String from_website = "";
            String statuscode = "";
            String description = "";
            String staffinfo_department = "";
            String staffinfo_staff = "";
            String staffinfo_mobile = "";
            String staffinfo_email = "";
            if (entity.containsKey("responsefor_organid")) {
                responsefor_organid = (String) entity.get("responsefor_organid");
            }
            if (entity.containsKey("responsefor_code")) {
                responsefor_code = (String) entity.get("responsefor_code");
            }
            if (entity.containsKey("responsefor_promulgationdate")) {
                responsefor_promulgationdate = (String) entity.get("responsefor_promulgationdate");
            }
            if (entity.containsKey("responsefor_documentid")) {
                responsefor_documentid = (String) entity.get("responsefor_documentid");
            }
            if (entity.containsKey("from_organid")) {
                from_organid = (String) entity.get("from_organid");
            }
            if (entity.containsKey("from_organizationincharge")) {
                from_organizationincharge = (String) entity.get("from_organizationincharge");
            }
            if (entity.containsKey("from_organname")) {
                from_organname = (String) entity.get("from_organname");
            }
            if (entity.containsKey("from_organadd")) {
                from_organadd = (String) entity.get("from_organadd");
            }
            if (entity.containsKey("from_email")) {
                from_email = (String) entity.get("from_email");
            }
            if (entity.containsKey("from_telephone")) {
                from_telephone = (String) entity.get("from_telephone");
            }
            if (entity.containsKey("from_fax")) {
                from_fax = (String) entity.get("from_fax");
            }
            if (entity.containsKey("from_website")) {
                from_website = (String) entity.get("from_website");
            }
            if (entity.containsKey("statuscode")) {
                statuscode = (String) entity.get("statuscode");
            }
            if (entity.containsKey("description")) {
                description = (String) entity.get("description");
            }
            if (entity.containsKey("staffinfo_department")) {
                staffinfo_department = (String) entity.get("staffinfo_department");
            }
            if (entity.containsKey("staffinfo_staff")) {
                staffinfo_staff = (String) entity.get("staffinfo_staff");
            }
            if (entity.containsKey("staffinfo_mobile")) {
                staffinfo_mobile = (String) entity.get("staffinfo_mobile");
            }
            if (entity.containsKey("staffinfo_email")) {
                staffinfo_email = (String) entity.get("staffinfo_email");
            }
            Content content = EdXMLBuild.create_status(
                    responsefor_organid,
                    responsefor_code,
                    responsefor_promulgationdate,
                    responsefor_documentid,
                    from_organid,
                    from_organizationincharge,
                    from_organname,
                    from_organadd,
                    from_email,
                    from_telephone,
                    from_fax,
                    from_website,
                    statuscode,
                    description,
                    staffinfo_department,
                    staffinfo_staff,
                    staffinfo_mobile,
                    staffinfo_email);
            File file = content.getContent();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            CustomResponse<InputStreamResource> response = new CustomResponse<InputStreamResource>(200, resource);
            return response.response_file(file.getName());
        } catch (Exception e) {
            CustomResponse<Object> response = new CustomResponse<>(500, e);
            return response.response();
        }
    }

    @PostMapping("/parse")
    public ResponseEntity<Object> parseStatus(
            @RequestPart(name = "file", required = true) MultipartFile multipartFile) {
        try {
            InputStream fileInputStream = multipartFile.getInputStream();
            JSONObject jsonObject = EdXMLParser.parseStatus(fileInputStream);
            CustomResponse<Object> response = new CustomResponse<>(200, jsonObject.toString());
            return response.response();
        } catch (Exception e) {
            CustomResponse<Object> response = new CustomResponse<>(500, e);
            return response.response();
        }
    }

}
