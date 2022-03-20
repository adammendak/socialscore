package com.adammendak.socialscore.service.impl;

import com.adammendak.socialscore.service.ExportService;
import com.adammendak.socialscore.service.ReplService;
import com.adammendak.socialscore.service.UrlService;
import com.adammendak.socialscore.service.dto.CommandDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
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
        while (isRunning) {
            parseInput(scanner.nextLine()).ifPresent(this::decideOnCommand);
        }
    }

    private void decideOnCommand(CommandDTO dto) {
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
                break;
            default:
                log.error("COMMAND NOT SUPPORTED : {}", dto.getCommand());
        }
    }

    private Optional<CommandDTO> parseInput(String nextLine) {
        String[] split = nextLine.split(" ");
        if (split.length < 4) {
            return Optional.of(CommandDTO.builder()
                    .command(split[0].toUpperCase())
                    .url(split.length > 1 ? split[1] : null)
                    .socialScore(split.length == 3 ? split[2] : null)
                    .build());
        } else {
            log.error("Wrong input : {}", nextLine);
            return Optional.empty();
        }
    }

    private void printInstructions() {
        System.out.println("SUPPORTED COMMANDS:");
        System.out.println(ADD + " [url] [score]");
        System.out.println(REMOVE + " [url]");
        System.out.println(EXPORT);
        System.out.println(TERMINATE);
    }

}
