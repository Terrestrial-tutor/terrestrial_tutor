import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorageService} from '../../../security/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router,
              private tokenService: TokenStorageService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const currentUser = this.tokenService.getUser();
    if (currentUser == null) {
      if (state.url.includes('login') || state.url.includes('registration')) {
        return true;
      } else {
        // Если пользователь не авторизован и пытается зайти на другую страницу, перенаправляем на страницу входа
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      if (state.url.includes('login') || state.url.includes('registration')) {
        this.router.navigate([currentUser.role.toLowerCase()]);
        return false
      }
      if (state.url.includes('admin') && currentUser.role != 'ADMIN') {
        this.router.navigate([currentUser.role.toLowerCase()]);
        return false;
      }
      if (state.url.includes('pupil') && currentUser.role != 'PUPIL') {
        this.router.navigate([currentUser.role.toLowerCase()]);
        return false;
      }
      if (state.url.includes('tutor') && currentUser.role != 'TUTOR') {
        this.router.navigate([currentUser.role.toLowerCase()]);
        return false;
      }
      if (state.url.includes('support') && currentUser.role != 'SUPPORT') {
        this.router.navigate([currentUser.role.toLowerCase()]);
        return false;
      }
      return true;
    }
  }
}
