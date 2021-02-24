package com.kreative.ual;

import java.awt.desktop.AboutHandler;
import java.awt.desktop.OpenFilesHandler;
import java.awt.desktop.PreferencesHandler;
import java.awt.desktop.PrintFilesHandler;
import java.awt.desktop.QuitHandler;
import java.lang.reflect.Method;

public class UniversalApplication {
	private AboutHandler aboutHandler = null;
	private OpenFilesHandler openFilesHandler = null;
	private PreferencesHandler preferencesHandler = null;
	private PrintFilesHandler printFilesHandler = null;
	private QuitHandler quitHandler = null;
	
	public UniversalApplication setAboutHandler(AboutHandler aboutHandler) {
		this.aboutHandler = aboutHandler;
		return this;
	}
	
	public UniversalApplication setOpenFileHandler(OpenFilesHandler openFilesHandler) {
		this.openFilesHandler = openFilesHandler;
		return this;
	}
	
	public UniversalApplication setPreferencesHandler(PreferencesHandler preferencesHandler) {
		this.preferencesHandler = preferencesHandler;
		return this;
	}
	
	public UniversalApplication setPrintFileHandler(PrintFilesHandler printFilesHandler) {
		this.printFilesHandler = printFilesHandler;
		return this;
	}
	
	public UniversalApplication setQuitHandler(QuitHandler quitHandler) {
		this.quitHandler = quitHandler;
		return this;
	}
	
	private static final String[][] classAndMethodNames = {
		{ "java.awt.Desktop", "getDesktop" },
		{ "com.kreative.ual.eawt.NewApplicationAdapter", "getInstance" },
		{ "com.kreative.ual.eawt.OldApplicationAdapter", "getInstance" },
	};
	
	public boolean register() {
		for (String[] classAndMethodName : classAndMethodNames) {
			try {
				Class<?> cls = Class.forName(classAndMethodName[0]);
				Method getInstance = cls.getMethod(classAndMethodName[1]);
				Object instance = getInstance.invoke(null);
				if (aboutHandler != null) {
					Method setAboutHandler = cls.getMethod("setAboutHandler", AboutHandler.class);
					setAboutHandler.invoke(instance, aboutHandler);
				}
				if (openFilesHandler != null) {
					Method setOpenFileHandler = cls.getMethod("setOpenFileHandler", OpenFilesHandler.class);
					setOpenFileHandler.invoke(instance, openFilesHandler);
				}
				if (preferencesHandler != null) {
					Method setPreferencesHandler = cls.getMethod("setPreferencesHandler", PreferencesHandler.class);
					setPreferencesHandler.invoke(instance, preferencesHandler);
				}
				if (printFilesHandler != null) {
					Method setPrintFileHandler = cls.getMethod("setPrintFileHandler", PrintFilesHandler.class);
					setPrintFileHandler.invoke(instance, printFilesHandler);
				}
				if (quitHandler != null) {
					Method setQuitHandler = cls.getMethod("setQuitHandler", QuitHandler.class);
					setQuitHandler.invoke(instance, quitHandler);
				}
				System.out.println("Registered app event handlers through " + classAndMethodName[0]);
				return true;
			} catch (Exception e) {
				System.out.println("Failed to register app event handlers through " + classAndMethodName[0] + ": " + e);
			}
		}
		return false;
	}
}
