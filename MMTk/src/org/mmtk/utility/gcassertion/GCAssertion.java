package org.mmtk.utility.gcassertion;

import org.mmtk.vm.VM;
import org.mmtk.vm.gcassertion.GCAssertionInternal;
import org.vmmagic.unboxed.ObjectReference;

public class GCAssertion {

    public static boolean IS_CHECK_DEAD_MARK = true; //  will control this by config later

    public static void assert_dead(Object p) {
        if (null == p) {
            return;
        }

        ObjectReference object = ObjectReference.fromObject(p);
        byte curValue = VM.objectModel.readAvailableByte(object); // .... 0000
        byte newValue = (byte) (curValue | GCAssertionInternal.ASSERT_DEAD_MARK); // .... 0001
        VM.objectModel.writeAvailableByte(object, newValue);
    }

    private GCAssertion() {}
}
