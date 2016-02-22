/**
 * Created by Admin on 17-02-2016.
 */
import Constants from '../constants';

export default (state = {}, action) => {
	if(action.type == Constants.DO_LOGIN) {
		return action.data;
	} else {
		return state;
	}
}