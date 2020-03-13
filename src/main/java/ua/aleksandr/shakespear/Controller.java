package ua.aleksandr.shakespear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Controller {
    private View view;
    private Model model;
    private BufferedReader reader;

    public Controller(Model model, View view) throws Exception {
        this.model = model;
        this.view = view;
    }

     void process() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        view.printChooseSonnet();
        view.printFromRequest();
        model.setSonnetsInRangeFrom(getValidNumber());
        view.printUntilRequest();
        model.setSonnetsInRangeUntil(getValidNumber());
        for(int pageNumber = model.getSonnetFrom(); pageNumber < model.getSonnetUntil(); pageNumber++){
            String url = model.createURL(pageNumber);
            String html = model.getTextFromURL(url);
            String [] text = model.parseText(html);
            model.buildMap(text,url);
        }
        view.printSearchedWordRequest();
        String keyWord = getValidWord();
        Map<String, Integer> map = model.searchWord(keyWord);
        if (map.isEmpty()) {
            view.printNoMatches();
        }
        view.printResult(map);
    }

    private int getValidNumber() throws IOException {
        int input = -1;
        do {
            try {
                input = Integer.parseInt(reader.readLine());
                if (input > 154 || input < 1) {
                    input = -1;
                    view.printWrongFormat();
                }
            } catch (NumberFormatException ex) {
                view.printWrongFormat();
            }
        } while (input == -1);
        return input;
    }

    private String getValidWord() throws IOException {
        String input;
        do {
            input = (reader.readLine());
            if (!input.matches("^[а-яА-Яa-zA-Z]+$")) {
                view.printWrongFormat();
                input = null;
            }

        } while (input == null);
        return input;
    }
}
