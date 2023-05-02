import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reclamation } from 'models/Reclamation';
import { ReclamationService } from 'Services/ReclamationService/reclamation.service';
import jsPDF from 'jspdf';
import 'jspdf-autotable';

@Component({
  selector: 'app-list-reclamation',
  templateUrl: './list-reclamation.component.html',
  styleUrls: ['./list-reclamation.component.scss']
})
export class ListReclamationComponent implements OnInit {
  selectedState:string="";
  ReclamationList:Reclamation[]=[];
  currentPage = 1;
  constructor(private ReclamationS:ReclamationService,
    private router: Router
    ) { 
    
  }

  ngOnInit(): void {
    this.getReclamation();
    console.log("here");
  }
  getReclamation(){
    this.ReclamationS.getReclamationsList().subscribe(data => {
      this.ReclamationList= data;
    
      console.log(this.ReclamationList);
    });
  }
  deleteReclamation(id:any){
    this.ReclamationS.deleteReclamation(Number(id)).subscribe( () =>this.getReclamation() );
  }
  navigateToUpdateReclamation(reclamation : Reclamation){
    this.router.navigate(['/update-reclamation'], { state: { reclamation: reclamation }});

  }
  filterReclamations(): Reclamation[] {
    if (!this.selectedState) {
      return this.ReclamationList;
    }

    return this.ReclamationList.filter(r => r.state === this.selectedState);
  }

  navigateTocreateReclamation(){
    this.router.navigate(['/create-reclamation']);
  }
}