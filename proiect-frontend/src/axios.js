import {
  axios,
  SERVER_BASE_URL,
  TOKEN,
} from './constants';

const instance = axios.create({
  baseURL: `${SERVER_BASE_URL}`,
});

instance.interceptors.request.use(
  config => {
    const jwt = localStorage.getItem(TOKEN);
    if (jwt) {
      config.headers['Authorization'] = jwt;
    }
    return config;
  },
)

export default instance;