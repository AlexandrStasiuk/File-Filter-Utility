package org.example;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.ParseException;
import org.example.handler.ParamsHandler;
import org.example.model.Params;

@Slf4j
public class FileFilterMain {
    public static void main(String[] args) {
        try {
            Params params = ParamsHandler.parse(args);
            FileFilterBusiness.startBusiness(params);
        } catch (ParseException e) {
            System.exit(2);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            log.error("Unexpected error received: {}", e.getMessage());
            System.exit(3);
        }
    }
}