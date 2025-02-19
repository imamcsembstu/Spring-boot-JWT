package com.imamcsembstu.spring.boot.jwt.common.utils;

import com.imamcsembstu.spring.boot.jwt.common.constant.ApplicationConstant;
import com.imamcsembstu.spring.boot.jwt.config.authentication.CustomUserDetails;
import com.imamcsembstu.spring.boot.jwt.model.role.Role;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;


@Component
public class Helper {

    public static void throwCaseSpecificDuplicateValidationMessage(String fieldName, boolean isActive){
        throw new RuntimeException(isActive ? fieldName + " is already exist" : fieldName + " is already exist in archive");
    }

    public static String getPartsNoSuffixForPartsNameResponse(String partsNo){
        return Objects.nonNull(partsNo) && !partsNo.isEmpty() ? " (" + partsNo + ")" : "";
    }

    public static User getCurrentUser() {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            user.setId(userDetails.getId());
            user.setFullName(userDetails.getFullName());
            user.setRole(new Role(userDetails.getRoleId(), userDetails.getRole()));
            return user;
        }
        return null;
    }

    public static String createDynamicCode(String errorCode, String... placeHolders) {
        StringBuilder builder = new StringBuilder(errorCode);
        if (Objects.isNull(placeHolders)) {
            return errorCode;
        }
        Arrays.stream(placeHolders).forEach(placeHolder ->
                builder.append(ApplicationConstant.MESSAGE_SEPARATOR).append(placeHolder));
        return builder.toString();
    }

    public static String generateSixDigitOtp() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000;
        return Integer.toString(number);
    }

    public static String generateAlphaNumericCode(int targetStringLength) {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57) || (i >= 65 && i <= 90))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String encodeUrl(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrl(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeToString(String generatedId) {
        String encodeToString = Base64.getEncoder().encodeToString
                (Base64.getEncoder().encodeToString(generatedId.getBytes()).getBytes());
        return encodeToString;
    }
    public static String decodeToString(String generatedId) {
        String decodeToString = new String(Base64.getDecoder().decode(Base64.getDecoder().decode(generatedId)));
        return decodeToString;
    }

}