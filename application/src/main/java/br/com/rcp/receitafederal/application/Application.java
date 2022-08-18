package br.com.rcp.receitafederal.application;

import br.com.rcp.receitafederal.domain.model.Account;
import br.com.rcp.receitafederal.domain.service.FileProcessorService;
import br.com.rcp.receitafederal.domain.service.FileWriterService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication(scanBasePackages = "br.com.rcp.receitafederal")
public class Application implements CommandLineRunner {

    @Setter(onMethod = @__({@Autowired}))
    private FileProcessorService fileProcessorService;

    @Setter(onMethod = @__({@Autowired}))
    private FileWriterService writerService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            throw new RuntimeException("At least one argument is required!");
        }

        if (args.length > 1) {
            throw new RuntimeException("Only one argument permited!");
        }

        String file = args[0];
        String out = "";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        if (file.contains(".")) {
            int at = file.indexOf(".");
            out = file.substring(0, at) + "_" + date + file.substring(at);
        } else {
            out = file + "_" + date;
        }

        List<Account> result = fileProcessorService.process(args[0]);
        System.out.println(result.toString());
        writerService.write(out, result);
    }
}
