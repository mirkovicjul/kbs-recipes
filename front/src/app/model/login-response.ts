export interface LoginResponse {
    success: boolean;
    token: string;
    message: string;
    admin: boolean;
}