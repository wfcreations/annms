/*
 * Copyright (c) Welsiton Ferreira (wfcreations@gmail.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of the WFCreation nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.wfcreations.annms.api.data.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.wfcreations.annms.api.data.value.ID;
import br.com.wfcreations.annms.api.data.value.IValue;
import br.com.wfcreations.lang.ArrayUtils;

public class ListType implements IType {

	private static final long serialVersionUID = 1L;

	private final List<ID> listValues;

	public ListType(ID[] listValues) {
		if (listValues.length == 0)
			throw new IllegalArgumentException("Empty list values");
		if (ArrayUtils.hasDuplicate(listValues))
			throw new IllegalArgumentException("Duplicated value");
		for (ID id : listValues)
			if (id == null)
				throw new IllegalArgumentException("Null or empty value");

		this.listValues = new ArrayList<>(Arrays.asList(listValues));
	}

	public ID getValuesAt(int index) {
		return listValues.get(index);
	}

	public int getListValuesNum() {
		return listValues.size();
	}

	@Override
	public boolean valid(IValue value) {
		return value instanceof ID && listValues.contains(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListType other = (ListType) obj;
		if (listValues == null)
			if (other.listValues != null)
				return false;
		if (listValues.size() != other.listValues.size())
			return false;
		for (int i = 0; i < listValues.size(); i++)
			if (!listValues.get(i).equals(other.listValues.get(i)))
				return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(listValues.toArray(new String[listValues.size()])).replace('[', '{').replace(']', '}');
	}
}