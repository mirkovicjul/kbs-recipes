import Constants from '../utils/Constants.js';
import LoginForm from '../components/LoginForm.js';

function isLoggedIn() {
    if (localStorage.getItem(Constants.accesstoken)) {
        return true;
    } else if (localStorage.getItem(Constants.refreshToken)) {
        // call api to get new access & refresh tokens
        return true;
    }
    return false;
}

export function redirectToLogin(render) {
    if (isLoggedIn()) {
        return render;
    } else {
        return <LoginForm />;
    }
}
