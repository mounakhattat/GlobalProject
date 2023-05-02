import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { UserComponent } from './user/user.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResetPasswordTokenComponent } from './reset-password-token/reset-password-token.component';
import { AuthGuard } from './service/auth.guard';
import { ConfirmationSMSComponent } from './confirmation-sms/confirmation-sms.component';
import { HomeComponent } from './home/home.component';

const routes: Routes =[
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
    
  },
  {path:"signup", component: SignupComponent},
  {path:"home", component: HomeComponent},

  {path:"login", component: LoginComponent},
  {path:"confirmationSMS", component: ConfirmationSMSComponent},

  {path:"profile", component: ProfileComponent , canActivate:[AuthGuard]},
  {path: 'resetPass',component:ResetPasswordComponent},
  {path: 'resetPassToken',component:ResetPasswordTokenComponent},




  {
    path: '',
    component: AdminLayoutComponent,

    children: [
      {path:"user", component:UserComponent  , canActivate:[AuthGuard]},
      {path:"signup", component: SignupComponent},


        {
      path: '',
      loadChildren: () => import('./layouts/admin-layout/admin-layout.module').then(x => x.AdminLayoutModule) , canActivate:[AuthGuard]
      
  }]},
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
       useHash: true
    })
  ],
 
})
export class AppRoutingModule { }
