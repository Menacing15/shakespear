package ua.aleksandr.shakespear;

public class Main {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        try {
            controller.process();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}