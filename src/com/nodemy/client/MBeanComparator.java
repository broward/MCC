package com.nodemy.client;

import java.util.Comparator;

import javax.management.ObjectInstance;

/**
 * Sorting comparator for mBeans.
 * 
 *
 * @param <T>
 */
public class MBeanComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		int result = 0;

		ObjectInstance mbean1 = (ObjectInstance) o1;
		ObjectInstance mbean2 = (ObjectInstance) o2;

		// if domains are the same, dig deeper to compare
		result = mbean1.getObjectName().getDomain()
				.compareTo(mbean2.getObjectName().getDomain());
		if (result == 0) {
			if (mbean1
					.getObjectName()
					.getCanonicalName()
					.equals(mbean1.getObjectName().getDomain()
							+ ":Type=Environment")) {
				System.out.println("yes, I found the "
						+ mbean1.getObjectName().getCanonicalName());
				result = -1;
			} else {
				result = mbean1.getObjectName().getCanonicalName()
						.compareTo(mbean2.getObjectName().getCanonicalName());
			}
		}

		return result;
	}

}
