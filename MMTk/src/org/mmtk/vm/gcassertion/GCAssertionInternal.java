package org.mmtk.vm.gcassertion;

import org.mmtk.utility.Log;
import org.mmtk.utility.gcassertion.GCAssertion;
import org.mmtk.vm.VM;
import org.vmmagic.unboxed.ObjectReference;

public class GCAssertionInternal {

    public static final byte ASSERT_DEAD_MARK = 1;

    public static void checkDeadMark(ObjectReference object) {
        if (!GCAssertion.IS_CHECK_DEAD_MARK) {
            return;
        }

        if (null == object) {
            return;
        }

        byte curValue = VM.objectModel.readAvailableByte(object);
        if ((curValue << 7) == (ASSERT_DEAD_MARK << 7)) {
            Log.writeln("this object should be dead, more info: ");
            VM.objectModel.dumpObject(object);
        }
    }

    private GCAssertionInternal() {}
}