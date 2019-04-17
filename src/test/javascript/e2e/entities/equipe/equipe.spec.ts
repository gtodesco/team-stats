/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EquipeComponentsPage, EquipeDeleteDialog, EquipeUpdatePage } from './equipe.page-object';

const expect = chai.expect;

describe('Equipe e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let equipeUpdatePage: EquipeUpdatePage;
    let equipeComponentsPage: EquipeComponentsPage;
    let equipeDeleteDialog: EquipeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Equipes', async () => {
        await navBarPage.goToEntity('equipe');
        equipeComponentsPage = new EquipeComponentsPage();
        await browser.wait(ec.visibilityOf(equipeComponentsPage.title), 5000);
        expect(await equipeComponentsPage.getTitle()).to.eq('Equipes');
    });

    it('should load create Equipe page', async () => {
        await equipeComponentsPage.clickOnCreateButton();
        equipeUpdatePage = new EquipeUpdatePage();
        expect(await equipeUpdatePage.getPageTitle()).to.eq('Create or edit a Equipe');
        await equipeUpdatePage.cancel();
    });

    it('should create and save Equipes', async () => {
        const nbButtonsBeforeCreate = await equipeComponentsPage.countDeleteButtons();

        await equipeComponentsPage.clickOnCreateButton();
        await promise.all([
            equipeUpdatePage.setNomeInput('nome'),
            equipeUpdatePage.setEmailScrumMasterInput('emailScrumMaster')
            // equipeUpdatePage.pessoasSelectLastOption(),
        ]);
        expect(await equipeUpdatePage.getNomeInput()).to.eq('nome');
        expect(await equipeUpdatePage.getEmailScrumMasterInput()).to.eq('emailScrumMaster');
        await equipeUpdatePage.save();
        expect(await equipeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await equipeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Equipe', async () => {
        const nbButtonsBeforeDelete = await equipeComponentsPage.countDeleteButtons();
        await equipeComponentsPage.clickOnLastDeleteButton();

        equipeDeleteDialog = new EquipeDeleteDialog();
        expect(await equipeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Equipe?');
        await equipeDeleteDialog.clickOnConfirmButton();

        expect(await equipeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
