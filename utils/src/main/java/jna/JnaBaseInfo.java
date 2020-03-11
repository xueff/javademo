package jna;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;

/**
 Created by lenovo on 2017/4/27.
 使用winID来获得窗口的类型和标题，然后发送消息或者其他操作
 *
 */
public class JnaBaseInfo {
    private static final int MAX_TITLE_LENGTH = 1024;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("title: " + JnaBaseInfo.getActiveWindowTitle());
            Thread.sleep(3000);
        }

    }

    public static WinDef.HWND getWindowByTitle(String title){
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow
                (null, title);
        if (hwnd == null) {
            System.out.println(title+" is not running");
        }

        return hwnd;
    }

    public static String getActiveWindowTitle(){
        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
        System.out.println("前台窗口名: " + Native.toString(buffer));
        return Native.toString(buffer);
    }

    public static String getWindowTitle(WinDef.HWND hwnd){
        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
        hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
        System.out.println("前台窗口名: " + Native.toString(buffer));
        return Native.toString(buffer);
    }

    public static String getActiveWindowNamePath(){
        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();

       return getWindowNamePath(hwnd);
    }

    public static String getWindowNamePath(WinDef.HWND hwnd){
        IntByReference procId = new IntByReference();
        User32.INSTANCE.GetWindowThreadProcessId(hwnd, procId);

        WinNT.HANDLE procHandle = Kernel32.INSTANCE.OpenProcess(
                Kernel32.PROCESS_QUERY_LIMITED_INFORMATION,
                false,
                procId.getValue()
        );
        char[] buffer = new char[4096];
        IntByReference bufferSize = new IntByReference(buffer.length);
        boolean success = Kernel32.INSTANCE.QueryFullProcessImageName(procHandle, 0, buffer, bufferSize);
        Kernel32.INSTANCE.CloseHandle(procHandle);
        return success ? new String(buffer, 0, bufferSize.getValue()) : null;
    }
}

