/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javarosa.formmanager.utility;

import org.javarosa.core.Context;
import org.javarosa.core.model.FormDef;

public class FormDefFetcher {
	IFormDefRetrievalMethod fetcher;

	public FormDef getFormDef(Context context) {
		if(fetcher != null) {
			return fetcher.retreiveFormDef(context);
		}
		else {
			return null;
		}
	}
	
	/**
	 * @return the fetcher
	 */
	public IFormDefRetrievalMethod getFetcher() {
		return fetcher;
	}

	/**
	 * @param fetcher the fetcher to set
	 */
	public void setFetcher(IFormDefRetrievalMethod fetcher) {
		this.fetcher = fetcher;
	}
	
	
}
