import {makeAutoObservable} from "mobx";
import {jwtDecode} from "jwt-decode";

export default class UserStore {
    constructor() {
        makeAutoObservable(this);
    }

    get isLoggedIn() {
        const token = localStorage.getItem("token");
        return !!token;
    }

    get userData() {
        const token = localStorage.getItem("token")!;
        const decodedToken: any = jwtDecode(token);
        return decodedToken;
    }

    get userId() {
        const token = localStorage.getItem("token")!;
        const decodedToken: any = jwtDecode(token);
        return decodedToken.id;
    }

    removeToken() {
        localStorage.removeItem("token");
    }

    setToken(token: string) {
        localStorage.setItem("token", token);
    }
}
