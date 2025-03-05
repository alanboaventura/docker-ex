package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping("/")
    public String hello() {
        final var serverPort = serverProperties.getPort();
        String executionEnvironment;

        if (serverPort == 9999) {
            //executionEnvironment = "dentro de um container Docker!";
            executionEnvironment = "dentro de um container Docker orquestrado pelo Kubernetes e monitorado pelo ArgoCD!!!";
        } else {
            executionEnvironment = "fora de um container, provavelmente no IntelliJ ou outro ambiente de desenvolvimento.";
        }

        System.out.println("*** Teste HCMINT ***");
        return buildHtml(executionEnvironment);
    }

    private static String buildHtml(String executionEnvironment) {
        String dockerImgTag = executionEnvironment.contains("Docker") ? "<img src='https://upload.wikimedia.org/wikipedia/commons/e/ea/Docker_%28container_engine%29_logo_%28cropped%29.png' style='width: 150px; margin-top: 20px;'>" : "";

        return "<html>" +
                "<head>" +
                "<title>Olá, HCMINT!</title>" +
                "<style>" +
                "body {" +
                "  font-family: Arial, sans-serif;" +
                "  background-color: #f4f4f9;" +
                "  margin: 0;" +
                "  padding: 0;" +
                "  display: flex;" +
                "  justify-content: center;" +
                "  align-items: center;" +
                "  height: 100vh;" +
                "  text-align: center;" +
                "}" +
                "h1 {" +
                "  color: #2c3e50;" +
                "  font-size: 40px;" +
                "  margin-bottom: 20px;" +
                "}" +
                "p {" +
                "  color: #7f8c8d;" +
                "  font-size: 20px;" +
                "  margin-top: 10px;" +
                "}" +
                "div {" +
                "  background-color: #ecf0f1;" +
                "  padding: 20px;" +
                "  border-radius: 10px;" +
                "  box-shadow: 0 4px 8px rgba(0,0,0,0.1);" +
                "}" +
                "a {" +
                "  display: inline-block;" +
                "  margin-top: 20px;" +
                "  color: #3498db;" +
                "  text-decoration: none;" +
                "  font-size: 18px;" +
                "  font-weight: bold;" +
                "}" +
                "a:hover {" +
                "  color: #2980b9;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div>" +
                "<h1>Olá, HCMINT!</h1>" +
                "<p>Essa aplicação Java está " + executionEnvironment + "</p>" +
                dockerImgTag +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
