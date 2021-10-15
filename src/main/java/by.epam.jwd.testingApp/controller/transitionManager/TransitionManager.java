package by.epam.jwd.testingApp.controller.transitionManager;

public class TransitionManager {

    private final Transition transitionByForward;
    private final Transition transitionByRedirect;

    private TransitionManager() {
        transitionByForward = new TransitionByForward();
        transitionByRedirect = new TransitionByRedirect();
    }

    private static class SingletonHolder {
        public static final TransitionManager HOLDER_INSTANCE = new TransitionManager();
    }

    public static TransitionManager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Transition getTransitionByForward() {
        return transitionByForward;
    }

    public Transition getTransitionByRedirect() {
        return transitionByRedirect;
    }
}
