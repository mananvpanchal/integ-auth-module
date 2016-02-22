/**
 * Created by Admin on 19-02-2016.
 */

import Constants from '../constants';

export default (state = false, action) => {
	switch (action.type) {
		case Constants.ACTION_START:
			return true;
		case Constants.ACTION_END:
			return false;
		default:
			return state;
	}
};