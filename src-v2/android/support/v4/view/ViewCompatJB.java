/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.ViewParent
 *  android.view.accessibility.AccessibilityNodeProvider
 */
package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeProvider;

class ViewCompatJB {
    ViewCompatJB() {
    }

    public static Object getAccessibilityNodeProvider(View view) {
        return view.getAccessibilityNodeProvider();
    }

    public static int getImportantForAccessibility(View view) {
        return view.getImportantForAccessibility();
    }

    public static ViewParent getParentForAccessibility(View view) {
        return view.getParentForAccessibility();
    }

    public static boolean hasTransientState(View view) {
        return view.hasTransientState();
    }

    public static boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
        return view.performAccessibilityAction(n2, bundle);
    }

    public static void postInvalidateOnAnimation(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void postInvalidateOnAnimation(View view, int n2, int n3, int n4, int n5) {
        view.postInvalidate(n2, n3, n4, n5);
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void postOnAnimationDelayed(View view, Runnable runnable, long l2) {
        view.postOnAnimationDelayed(runnable, l2);
    }

    public static void setHasTransientState(View view, boolean bl2) {
        view.setHasTransientState(bl2);
    }

    public static void setImportantForAccessibility(View view, int n2) {
        view.setImportantForAccessibility(n2);
    }
}

