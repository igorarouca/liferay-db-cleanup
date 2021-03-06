package com.cambiahealth.portal.hook.service;

import static com.cambiahealth.portal.hook.search.Field.UUID;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceWrapper;

public class CambiaJournalArticleLocalService 
	extends JournalArticleLocalServiceWrapper {

	public CambiaJournalArticleLocalService(
		JournalArticleLocalService journalArticleLocalService) {

		super(journalArticleLocalService);
	}

	@Override
	public Hits search(
			long companyId, long groupId, long classNameId, String articleId, 
			String title, String description, String content, String type, 
			String status, String structureId, String templateId, 
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, Sort sort)
		throws SystemException {

		try {
			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(andSearch);

			Map<String, Serializable> attributes =
				new HashMap<String, Serializable>();

			attributes.put(Field.CLASS_NAME_ID, classNameId);
			attributes.put(Field.CONTENT, content);
			attributes.put(Field.DESCRIPTION, description);
			attributes.put(Field.STATUS, status);
			attributes.put(Field.TITLE, title);
			attributes.put(Field.TYPE, type);

			// *** Customization begins here ***
			// Using 'description' to pass UUID in the search query
			attributes.put(UUID, description);
			// *** Customization ends here ***

			attributes.put("articleId", articleId);
			attributes.put("params", params);
			attributes.put("structureId", structureId);
			attributes.put("templateId", templateId);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);
			searchContext.setGroupIds(new long[] {groupId});

			if (params != null) {
				String keywords = (String)params.remove("keywords");

				if (Validator.isNotNull(keywords)) {
					searchContext.setKeywords(keywords);
				}
			}

			QueryConfig queryConfig = new QueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			searchContext.setQueryConfig(queryConfig);

			searchContext.setSorts(new Sort[] {sort});
			searchContext.setStart(start);

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				JournalArticle.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

}