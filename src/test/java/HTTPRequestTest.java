import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;

public class HTTPRequestTest {
    private String host = "nj-rp.abc.com";

    private String token = "cwt=AAEBHAEFAAAAAAAFFQAAANz03DsuQ6xAB-U7K8eNAACBEBUTVWulRKhftCre06OKSveCArDzgyCF8uq9xjdY-yXWiq-1twHelxo3mORL6dQSFbo95XI0rIYIlzGCLEDI1QgNEBDrIobFox9WpyA_EhuC5Mk";

    private String boundary = "77f2569d-c005-442b-b856-782305305e5f";

    private String applicationsUrl = "/ucwa/oauth/v1/applications";

    private String applicationId = "101055840064";

    private String username = "abc\\atest";

    private String password = "";

    private static String TOKEN_TYPE = "Bearer";

    private String userAgent = "IPuserstatus";
    private String endpointId = "101df5d6-e020-4844-b9e4-568d375a589e";

    @Test
    public void test() {
        //pollStatus();
        //authenticate();
        //registerApp();
    }

    @Test
    public void test2() {
        List<String> emails = new ArrayList<>();
        emails.add("abc.test@abc.com");
        batchRequest(emails, 1);
    }

    void pollStatus() {
        List<String> emails = new ArrayList<>();

        for (int i = 0; i < 94; i++) {
            emails.add(i + "");
        }

        int size = emails.size();
        int fromIndex = 0;

        while (fromIndex < size) {
            List<String> subList = emails.subList(fromIndex, Math.min(fromIndex + 10, size));
            System.out.println(subList.toString());
            fromIndex += 10;
        }
    }

    private void authenticate() {
        try {
            HttpResponse<String> response = Unirest.post("https://" + host + "/WebTicket/oauthtoken")
                .header("content-type", "application/x-www-form-urlencoded")
                .body(String.format("grant_type=password&username=%s&password=%s", username, password))
                .asString();

            if (response.getStatus() == HttpStatus.SC_OK) {
                System.out.println(response.getBody());

                JSONObject jsonObject = new JSONObject(response.getBody());
                String test = jsonObject.getString("access_token");


                int expiresIn = jsonObject.getInt("expires_in");
                System.out.println(expiresIn);
            } else {
                System.out.println(response.getStatus());
                System.out.println("Response1 \n" + response.getBody());
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void registerApp() {
        String culture = "en-US";

        String body = "{" +
            "\"UserAgent\":\"" + userAgent + "\"," +
            "\"EndpointId\":\"" + endpointId + "\"," +
            "\"Culture\":\"" + culture + "\"" +
            "}";

        try {
            HttpResponse<String> response = Unirest.post("https://" + host + "/ucwa/oauth/v1/applications")
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .header("Accept", "application/json")
                .body(body)
                .asString();

            System.out.println(response.getBody());

            if (response.getStatus() == HttpStatus.SC_OK || response.getStatus() == HttpStatus.SC_CREATED) {
                JSONObject jsonObject = new JSONObject(response.getBody());
                String applicationUrl = jsonObject.getJSONObject("_links").getJSONObject("self").getString("href");
                String[] arr = applicationUrl.split("/");
                String applicationId = arr[arr.length - 1];
                System.out.println(applicationId);

            } else {
                System.out.println(response.getStatus());
                System.out.println("Response1 \n" + response.getBody());
            }
        } catch (Exception e) {
            //log.error("Error: {}", e.getMessage());
        }
    }

    private Map<String, Boolean> batchRequest(List<String> emails, int retry) {

        List<String> parts = emails.stream()
            .map(this::createDataPart)
            .collect(Collectors.toList());

        String body = parts.stream().collect(Collectors.joining("\r\n"));
        body += "\r\n\r\n--" + boundary + "--\r\n";

        try {
            HttpResponse<String> response =
                Unirest.post(getBatchUrl())
                    .header("authorization", TOKEN_TYPE + " " + token)
                    .header("content-type", "multipart/batching;boundary=" + boundary)
                    .header("accept", "multipart/batching")
                    .body(body)
                    .asString();

            System.out.println(response.getStatus());
            System.out.println("Response1 \n" + response.getBody());

            if (response.getStatus() == HttpStatus.SC_OK) {

                Map<String, Boolean> statusMap = processMessage(response.getHeaders(), response.getBody());

                System.out.println(statusMap.toString());
                return statusMap;

            } else if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
                JSONObject jsonObject = new JSONObject(response.getBody());
                if (jsonObject.has("subcode") && "ApplicationNotFound".equals(jsonObject.getString("subcode"))) {

                    System.out.println("Application not found!");
                }

            } else {

            }


        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

    Map<String, Boolean> processMessage(Headers headers, String body) {
        String boundary = processHeader(headers);
        List<String> messages = processBody(boundary, body);

        Map<String, Boolean> statusMap = new HashMap<>();

        for (String message : messages) {
            JSONObject jsonObject = new JSONObject(message);
            String presenceUrl = jsonObject.getJSONObject("_links").getJSONObject("self").getString("href");
            String email = StringUtils.substringBetween(presenceUrl, "people/", "/presence");

            boolean isOnPhone = false;
            if(jsonObject.has("activity")) {
                String activity = jsonObject.getString("activity");
                isOnPhone = "on-the-phone".equals(activity);
            }

            statusMap.put(email, isOnPhone);
        }

        return statusMap;
    }

    String processHeader(Headers headers) {
        String temp = headers.getFirst("Content-Type");
        String boundary = StringUtils.substringBetween(temp, "boundary=\"", "\"");

        System.out.println(boundary);
        return boundary;
    }

    List<String> processBody(String boundary, String body) {
        List<String> messages = new ArrayList<>();
        String[] data = body.split("--" + boundary);
        for (String dataPart : data) {
            if (!dataPart.isEmpty() && !dataPart.equals("--\r\n")) {
                String[] partData = dataPart.split("\r\n");
                String message = partData[partData.length - 1];
                messages.add(message);
            }
        }
        return messages;
    }

    String getBatchUrl() {
        return "https://" + host + getApplicationUrl() + "/batch";
    }

    String getApplicationUrl() {
        return applicationsUrl + "/" + applicationId;
    }

    String getPresenceUrl(String email) {
        return getApplicationUrl() + "/people/" + email + "/presence";
    }

    String createDataPart(String email) {
        String dataPart = "\r\n--" + boundary;
        dataPart += "\r\nContent-Type: application/http; msgtype=request\r\n";
        dataPart += "\r\n" + "GET " + getPresenceUrl(email) + " HTTP/1.1";
        dataPart += "\r\n" + "Host: " + host;
        dataPart += "\r\n" + "Accept: " + "application/json";
        dataPart += "\r\n";
        return dataPart;
    }

}
