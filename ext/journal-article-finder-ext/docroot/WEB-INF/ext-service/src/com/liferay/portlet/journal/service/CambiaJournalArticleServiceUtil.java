/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.journal.service;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portlet.journal.model.JournalArticle;

public class CambiaJournalArticleServiceUtil {

	public static CambiaJournalArticleService getService() {
		if (_service == null) {
			_service = (CambiaJournalArticleService) PortalBeanLocatorUtil
				.locate(JournalArticleService.class.getName());

			ReferenceRegistry.registerReference(
				CambiaJournalArticleServiceUtil.class, "_service");
		}

		return _service;
	}

	public static List<JournalArticle> search(
			long companyId, long groupId, long classNameId, String uuid,
			String articleId, Double version, String title, String description,
			String content, String type, String structureId, String templateId,
			Date displayDateGT, Date displayDateLT, int status, Date reviewDate,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		return getService().search(
			companyId, groupId, classNameId, uuid, articleId, version, title,
			description, content, type, structureId, templateId, displayDateGT,
			displayDateLT, status, reviewDate, andOperator, start, end, obc);
	}

	public static int searchCount(
			long companyId, long groupId, long classNameId, String uuid,
			String articleId, Double version, String title, String description,
			String content, String type, String structureId, String templateId,
			Date displayDateGT, Date displayDateLT, int status, Date reviewDate,
			boolean andOperator)
		throws SystemException {

		return getService().searchCount(
			companyId, groupId, classNameId, uuid, articleId, version, title,
			description, content, type, structureId, templateId, displayDateGT,
			displayDateLT, status, reviewDate, andOperator);
	}

	private static CambiaJournalArticleService _service;

}