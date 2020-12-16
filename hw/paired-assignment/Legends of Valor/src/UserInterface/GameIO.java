package UserInterface;

public class GameIO implements Confirmation {

    protected final TextMessageDecorator tmd;

    public GameIO(){
        tmd = new TextMessageDecorator();
    }

//    public boolean makeConfirmation(String action){
//        System.out.println(tmd.addColor("red", "Are you sure to " + action + "?(Y/N)"));
//        Scanner scanner = new Scanner(System.in);
//        String s;
//
//        while (true){
//            try {
//                s = scanner.nextLine();
//                if (s.equalsIgnoreCase("Y")
//                        || s.equalsIgnoreCase("N")) {
//                    return s.equalsIgnoreCase("Y");
//                } else {
//                    System.out.println(tmd.addColor("red",  "Invalid input. Please enter Y/N:"));
//                }
//            } catch (InputMismatchException e){
//                System.out.println(tmd.addColor("red", "Invalid input. Please enter again:"));
//                scanner.next();
//            }
//        }
//    }

}
