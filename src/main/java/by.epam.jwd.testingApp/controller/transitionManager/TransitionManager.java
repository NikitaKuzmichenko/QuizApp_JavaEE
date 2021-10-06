package by.epam.jwd.testingApp.controller.transitionManager;

public class TransitionManager {

    private static TransitionManager instance;

    private final Transition transitionByForward;
    private final Transition transitionByRedirect;

    private TransitionManager(){
        transitionByForward = new TransitionByForward();
        transitionByRedirect = new TransitionByRedirect();
    }
    public static TransitionManager newInstance() {
        TransitionManager localInstance = instance;
        if (localInstance == null) {
            synchronized (TransitionManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TransitionManager();
                }
            }
        }
        return localInstance;
    }

    public Transition getTransitionByForward() {
        return transitionByForward;
    }

    public Transition getTransitionByRedirect() {
        return transitionByRedirect;
    }
}
