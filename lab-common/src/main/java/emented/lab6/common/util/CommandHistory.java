package emented.lab6.common.util;

import java.util.ArrayDeque;

public class CommandHistory {
    private final ArrayDeque<String> history = new ArrayDeque<>(9);

    public ArrayDeque<String> getHistory() {
        return history;
    }

    public void pushCommand(String name) {
        history.addFirst(name);
        int dequeOverflow = 10;
        if (history.size() == dequeOverflow) {
            history.pollLast();
        }
    }
}
