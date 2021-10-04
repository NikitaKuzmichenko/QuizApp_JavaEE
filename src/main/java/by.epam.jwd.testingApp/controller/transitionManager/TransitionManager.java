package by.epam.jwd.testingApp.controller.transitionManager;

public class TransitionManager {

    private static TransitionManager instance;

    private final TransitionByForward transitionByForward;
    private final TransitionByRedirect transitionByRedirect;

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

    public TransitionByForward getTransitionByForward() {
        return transitionByForward;
    }

    public TransitionByRedirect getTransitionByRedirect() {
        return transitionByRedirect;
    }
}
