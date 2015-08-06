package com.cambiahealth.portal.cleanup.cleaners.site.impl;

import com.cambiahealth.portal.cleanup.cleaners.site.SiteCleaner;
import com.cambiahealth.portal.cleanup.cleaners.site.SiteCleanerFactory;

import java.util.List;
public class SiteCleanerFactoryImpl implements SiteCleanerFactory {

	public SiteCleanerFactoryImpl() {
	}

	@Override
	public SiteCleaner getParallelSiteCleaner(
		long companyId, List<String> siteNames) {

		return new ParallelSiteCleaner(companyId, siteNames);
	}

	@Override
	public SiteCleaner getSequentialSiteCleaner(
		long companyId, List<String> siteNames) {

		return new SequentialSiteCleaner(companyId, siteNames);
	}

}