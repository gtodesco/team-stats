/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PessoaComponentsPage, PessoaDeleteDialog, PessoaUpdatePage } from './pessoa.page-object';

const expect = chai.expect;

describe('Pessoa e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let pessoaUpdatePage: PessoaUpdatePage;
    let pessoaComponentsPage: PessoaComponentsPage;
    let pessoaDeleteDialog: PessoaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Pessoas', async () => {
        await navBarPage.goToEntity('pessoa');
        pessoaComponentsPage = new PessoaComponentsPage();
        await browser.wait(ec.visibilityOf(pessoaComponentsPage.title), 5000);
        expect(await pessoaComponentsPage.getTitle()).to.eq('Pessoas');
    });

    it('should load create Pessoa page', async () => {
        await pessoaComponentsPage.clickOnCreateButton();
        pessoaUpdatePage = new PessoaUpdatePage();
        expect(await pessoaUpdatePage.getPageTitle()).to.eq('Create or edit a Pessoa');
        await pessoaUpdatePage.cancel();
    });

    it('should create and save Pessoas', async () => {
        const nbButtonsBeforeCreate = await pessoaComponentsPage.countDeleteButtons();

        await pessoaComponentsPage.clickOnCreateButton();
        await promise.all([
            pessoaUpdatePage.setNomeInput('nome'),
            pessoaUpdatePage.setEmailInput('email'),
            pessoaUpdatePage.setPasswordInput('password'),
            pessoaUpdatePage.setAtivConcluidasInput('5'),
            pessoaUpdatePage.setPontEntregueInput('5')
        ]);
        expect(await pessoaUpdatePage.getNomeInput()).to.eq('nome');
        expect(await pessoaUpdatePage.getEmailInput()).to.eq('email');
        expect(await pessoaUpdatePage.getPasswordInput()).to.eq('password');
        expect(await pessoaUpdatePage.getAtivConcluidasInput()).to.eq('5');
        expect(await pessoaUpdatePage.getPontEntregueInput()).to.eq('5');
        await pessoaUpdatePage.save();
        expect(await pessoaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await pessoaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Pessoa', async () => {
        const nbButtonsBeforeDelete = await pessoaComponentsPage.countDeleteButtons();
        await pessoaComponentsPage.clickOnLastDeleteButton();

        pessoaDeleteDialog = new PessoaDeleteDialog();
        expect(await pessoaDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Pessoa?');
        await pessoaDeleteDialog.clickOnConfirmButton();

        expect(await pessoaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
