import {
    axios,
    TOKEN,
    SERVER_BASE_URL,
    IS_LOGGED_IN,
  
} from './constants';

export async function validateToken() {
    const token = localStorage.getItem(TOKEN);
    localStorage.setItem(IS_LOGGED_IN, false);

    if (token !== null) {
        const response = await axios.post(`${SERVER_BASE_URL}/validate-token`, {
            token: token.replace("Bearer ", "")
        })
        if (response.data) {
            localStorage.setItem(IS_LOGGED_IN, true);
        }
    }
}