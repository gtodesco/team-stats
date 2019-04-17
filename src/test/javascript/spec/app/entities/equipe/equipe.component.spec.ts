/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TeamStatsTestModule } from '../../../test.module';
import { EquipeComponent } from 'app/entities/equipe/equipe.component';
import { EquipeService } from 'app/entities/equipe/equipe.service';
import { Equipe } from 'app/shared/model/equipe.model';

describe('Component Tests', () => {
    describe('Equipe Management Component', () => {
        let comp: EquipeComponent;
        let fixture: ComponentFixture<EquipeComponent>;
        let service: EquipeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TeamStatsTestModule],
                declarations: [EquipeComponent],
                providers: []
            })
                .overrideTemplate(EquipeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EquipeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EquipeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Equipe(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.equipes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
