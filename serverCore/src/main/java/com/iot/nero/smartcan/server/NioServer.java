package com.iot.nero.smartcan.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/6
 * Time   4:32 PM
 */
public class NioServer {

    public void run(Selector selector) throws IOException {
        while (!Thread.interrupted()) {
            int intReadySelectionKeyCount = selector.select();
            if (intReadySelectionKeyCount == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
            while (selectionKeyIterator.hasNext()) {
                this.dispatch(selectionKeyIterator.next());
            }
            selectionKeySet.clear();
        }
    }

    void dispatch(SelectionKey selectionKey) {
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

}
