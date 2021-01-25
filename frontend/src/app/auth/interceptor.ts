import { Injectable } from "@angular/core";
import {
    HttpEvent, HttpInterceptor, HttpHandler,
    HttpRequest, HttpErrorResponse
} from "@angular/common/http";
import { throwError, Observable} from "rxjs";
import { catchError } from "rxjs/operators";
import {AuthenticationService,  User} from "@nuvem/angular-base";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    private login;

    constructor(login: AuthenticationService<User>) {
        this.login = login;
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error && error.status === 302) {
                    this.login.logout();
                } else {
                    return throwError(error);
                }
            })
        );
    }
}
