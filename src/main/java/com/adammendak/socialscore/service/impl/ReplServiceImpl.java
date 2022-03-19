package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.service.ExportService;
import com.adammendak.socialscore.service.ReplService;
import com.adammendak.socialscore.service.UrlService;
import com.adammendak.socialscore.service.dto.CommandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ReplServiceImpl implements ReplService {

    public static final String ADD = "ADD";
    public static final String REMOVE = "REMOVE";
    public static final String TERMINATE = "TERMINATE";
    public static final String EXPORT = "EXPORT";

    private final Scanner scanner = new Scanner(System.in);

    private final UrlService urlService;
    private final ExportService exportService;

    private boolean isRunning = true;

    @Override
    public void run() {
        printInstructions();

        while(isRunning) {
            CommandDTO dto = parseInput(scanner.nextLine());
            switch (dto.getCommand()) {
                case ADD:
                    urlService.addUrl(dto);
                    break;
                case REMOVE:
                    urlService.removeUrl(dto.getUrl());
                    break;
                case EXPORT:
                    exportService.export();
                    break;
                case TERMINATE:
                    isRunning = false;
                default:
                    System.out.println("COMMAND NOT SUPPORTED !");
            }
        }

    }

    private CommandDTO parseInput(String nextLine) {
        //todo refactor
        String[] split = nextLine.split(" ");
        return CommandDTO.builder()
                .command(split[0])
                .url(split.length == 3 ? split[1] : null)
                .socialScore(split.length == 3 ? split[2] : null)
                .build();
    }

    private void printInstructions() {
        System.out.println("SUPPORTED COMMANDS:");
        System.out.println(ADD + " [url] [score]");
        System.out.println(REMOVE + " [url]");
        System.out.println(EXPORT);
        System.out.println(TERMINATE);
    }

}
