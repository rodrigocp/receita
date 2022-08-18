package br.com.rcp.receitafederal.service;


import br.com.rcp.receitafederal.domain.error.InvalidFileFormatException;
import br.com.rcp.receitafederal.domain.service.FileValidatorService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DefaultFileValidatorService implements FileValidatorService {
    private static final String COLUMN_SEPARATOR = ";";
    private static final String LINE_SEPARATOR = "\\r?\\n";
    private static final int COLUMN_COUNT = 4;
    private static final Pattern pattern = Pattern.compile("-?\\d+(,\\d+)?");

    @Override
    public boolean isValidFormat(String data) {
        try {
            if (!data.contains(COLUMN_SEPARATOR)) {
                throw new InvalidFileFormatException("Invalid file format!");
            }

            for (String line : data.substring(data.indexOf('\n') + 1).split(LINE_SEPARATOR)) {
                String[] columns = line.split(COLUMN_SEPARATOR);

                if (columns.length > COLUMN_COUNT) {
                    throw new InvalidFileFormatException("Invalid file format!");
                }

                if (columns[0].length() > 4) {
                    throw new InvalidFileFormatException("Collumn 'agency' have more characters than expected!");
                }

                if (columns[1].length() > 7) {
                    throw new InvalidFileFormatException("Collumn 'account' have more characters than expected!");
                }

                if (!columns[1].contains("-")) {
                    throw new InvalidFileFormatException("Collumn 'account' does not have '00000-0' format!");
                }

                if (!pattern.matcher(columns[2]).matches()) {
                    throw new InvalidFileFormatException("Collumn 'balance' is not a number!");
                }

                if (columns[3].length() > 1) {
                    throw new InvalidFileFormatException("Collumn 'status' does not have a valid format!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
