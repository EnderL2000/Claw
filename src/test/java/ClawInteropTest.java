import java.util.LinkedList;
import java.util.List;

public class ClawInteropTest {

    private boolean verbose;
    private ClawApplication clawApplication = new ClawApplication();
    private Transition transitionEngine = new Transition(clawApplication) {
        @Override
        public void transition(double deltaTime) {
            if(verbose) {
                System.out.println("Transitioning...");
            }
            super.transition(deltaTime);
        }

        @Override
        public void start() {
            if(verbose) {
                System.out.println("Transition Started");
            }
            super.start();
        }

        @Override
        public void end() {
            if(verbose) {
                System.out.println("Transition Ended");
            }
            super.end();
        }

        @Override
        public boolean isFinished() {
            return true;
        }
    };

    public ClawInteropTest() {
        this(false);
    }

    public ClawInteropTest(boolean verbose) {
        this.verbose = verbose;
        transitionEngine.bind();
    }

    public void run() {
        final Screen startScreen = new Screen("Start", clawApplication, null);
        final Screen basicScreen = new Screen("Basic", clawApplication, null);
        final Screen twoWayScreen = new Screen("TwoWay", clawApplication, null);
        final Screen backScreen = new Screen("Back", clawApplication, null);
        final Screen finalScreen = new Screen("Final", clawApplication, null);

        Item basicLever = new Item(new LinkedList<>(), 20, 20, 30, 30, 1) {
            @Override
            public void useItem() {
                setState(true);
            }
        };
        basicLever.setState(false);

        List<Event> conditionalButtonEvents = new LinkedList<>();
        conditionalButtonEvents.add(
                new Event() {
                    public boolean conditionsMet() {
                        return (boolean)basicLever.getState();
                    }
                }
        );

        Item conditionalButton = new Item(conditionalButtonEvents, 40, 40, 60, 60, 1) {
            @Override
            public void useItem() {
                backScreen.addPortal(new Portal(finalScreen, 20, 20, 80, 80, 1));
                setState("Pressed");
            }
        };
        conditionalButton.setState("Not Pressed");

        startScreen.addPortal(new Portal(basicScreen, 0, 0, 100, 100, 0));

        basicScreen.addPortal(new Portal(twoWayScreen, 20, 20, 80, 80, 0));
        basicScreen.addItem(basicLever);

        twoWayScreen.addPortal(new Portal(basicScreen, 0, 0, 49, 100, 0));
        twoWayScreen.addPortal(new Portal(backScreen, 50, 0, 100, 100, 0));

        backScreen.addPortal(new Portal(twoWayScreen, 20, 20, 80, 80, 0));
        backScreen.addItem(conditionalButton);

        clawApplication.addScreen(startScreen);
        clawApplication.addScreen(basicScreen);
        clawApplication.addScreen(twoWayScreen);
        clawApplication.addScreen(backScreen);

        clawApplication.setCurrentScreen(startScreen);

        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(0, 0);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(0, 0);
        if (verbose) {
            System.out.println(clawApplication);
        }

        if (verbose) {
            System.out.println("Lever flipped: " + basicLever.getState());
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(50, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(75, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(50, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        if (verbose) {
            System.out.println("Condition Button: " + conditionalButton.getState());
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(30, 30);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(25, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(25, 25);
        if (verbose) {
            System.out.println(clawApplication);
        }

        if (verbose) {
            System.out.println("Lever flipped: " + basicLever.getState());
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(50, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(75, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(50, 50);
        if (verbose) {
            System.out.println(clawApplication);
        }

        if (verbose) {
            System.out.println("Condition Button: " + conditionalButton.getState());
        }

        clawApplication.update(0.0D);
        if (verbose) {
            System.out.println();
        }

        clawApplication.handleClick(20, 20);
        if (verbose) {
            System.out.println(clawApplication);
        }
    }

    public static void main(String[] args) {
        ClawInteropTest clawInteropTest = new ClawInteropTest(true);
        clawInteropTest.run();
    }
}