import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListTracksComponent } from './list-tracks/list-tracks.component';
import { LoginComponent } from './login/login.component';
import { SearchTracksComponent } from './search-tracks/search-tracks.component';
import { SignupComponent } from './signup/signup.component';
import { WelcomeComponent } from './welcome/welcome.component';




const routes: Routes = [
  {
    path:"", redirectTo:"login", pathMatch:"full"
  },
 
  {
    path:"login",component:LoginComponent
  },
  {
    path:"signup",component:SignupComponent
  },
  {
    path:"welcome",component:WelcomeComponent
  },
  {
    path:"search",component:SearchTracksComponent
  },
  {
    path:"listfavouritetracks",component:ListTracksComponent
  }
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
