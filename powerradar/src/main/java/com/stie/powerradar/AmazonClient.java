package com.stie.powerradar;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
@Service
public class AmazonClient {


    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
     String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${aws.access.key}")//("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${aws.secret.key}")//("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    public String uploadFile(File file,String type) {
        String fileUrl = "";
        try {

            String fileName = type+"_"+LocalDate.now()+"_"+LocalTime.now(ZoneId.of("Asia/Kolkata"))+".json";
            fileUrl = endpointUrl + "/" + bucketName +"/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }
}
