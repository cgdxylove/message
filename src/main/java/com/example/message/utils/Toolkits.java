package com.example.message.utils;


public class Toolkits {
	/**
	 * 获取指定异常的消息String
	 * @param e
	 * @return String 异常堆栈的字符串表达式
	 */
	public static String getExceptionInfo(Exception e) {
		if (e == null)
			return null;
		StringBuffer result = new StringBuffer();
		result.append("【");
		try {
			result.append(e.getMessage() + "\n");
			StackTraceElement[] elements = e.getStackTrace();
			for (int i = 0; i < elements.length; i++) {
				result.append(elements[i].toString() + "\n");
			}
		} catch (Exception ex) {
			return result.toString();
		}
		result.append("】");
		return result.toString();
	}

	/**
	 * 获取指定异常的消息String
	 * 
	 * @param e
	 *            当前抛出的异常
	 * @param length
	 *            返回异常消息的最大长度
	 * @return String 异常堆栈的字符串表达式
	 */
	public static String getExceptionInfo(Exception e, int length) {
		if (e == null)
			return null;
		StringBuffer result = new StringBuffer();
		result.append("【");
		result.append(e.getMessage() + "\n");
		try {
			StackTraceElement[] elements = e.getStackTrace();
			for (int i = 0; i < elements.length; i++) {
				result.append(elements[i].toString() + "\n");
			}
		} catch (Exception ex) {
			return result.toString().substring(0, getMaxLen(result.toString(), length));
		}
		result.append("】");
		return result.toString().substring(0, getMaxLen(result.toString(), length));
	}
	
	/**
	 * 获取指定异常的消息String
	 * 
	 * @param e
	 *            当前抛出的异常
	 * @return String 异常堆栈的字符串表达式
	 */
	public static String getExceptionInfo(Throwable e) {
		if (e == null)
			return null;
		StringBuffer result = new StringBuffer();
		result.append("【");
		try {
			result.append(e.getMessage() + "\n");
			StackTraceElement[] elements = e.getStackTrace();
			for (int i = 0; i < elements.length; i++) {
				result.append(elements[i].toString() + "\n");
			}
		} catch (Exception ex) {
			return result.toString();
		}
		result.append("】");
		return result.toString();
	}

	/**
	 * 获取指定异常的消息String
	 * 
	 * @param e
	 *            当前抛出的异常
	 * @param length
	 *            返回异常消息的最大长度
	 * @return String 异常堆栈的字符串表达式
	 */
	public static String getExceptionInfo(Throwable e, int length) {
		if (e == null)
			return null;
		StringBuffer result = new StringBuffer();
		result.append("【");
		result.append(e.getMessage() + "\n");
		try {
			StackTraceElement[] elements = e.getStackTrace();
			for (int i = 0; i < elements.length; i++) {
				result.append(elements[i].toString() + "\n");
			}
		} catch (Exception ex) {
			return result.toString().substring(0, getMaxLen(result.toString(), length));
		}
		result.append("】");
		return result.toString().substring(0, getMaxLen(result.toString(), length));
	}

	private static int getMaxLen(String msg, int reqLength) {
		if (StringUtils.isBlank(msg)) {
			return 0;
		}
		if (msg.length() > reqLength)
			return reqLength;
		else
			return msg.length();
	}
}
