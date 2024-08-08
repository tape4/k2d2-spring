package knet_chanllenge.k2d2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @Value("${commit.hash}")
    public String commitHash;

    @ResponseBody
    @GetMapping("/health-check")
    public String healthCheck() {
        return commitHash;
    }
}
