import "@testing-library/jest-dom/extend-expect";
import { cleanup } from '@testing-library/react';
import React from 'react';
import renderer from 'react-test-renderer';
import AuthenticationForm from '../AuthenticationForm';


afterEach(cleanup);

it("matches-snapshot-register", () => {
    const tree = renderer.create(<AuthenticationForm isLogin={false} onSubmit={jest.fn()}></AuthenticationForm>).toJSON();
    expect(tree).toMatchSnapshot();
});

it("matches-snapshot-login", () => {
    const tree = renderer.create(<AuthenticationForm isLogin={true} onSubmit={jest.fn()}></ AuthenticationForm>).toJSON();
    expect(tree).toMatchSnapshot();
});