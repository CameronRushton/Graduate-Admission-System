export function configure(config) {
  config.globalResources(
      [
          PLATFORM.moduleName('./components/header/header'),
		  PLATFORM.moduleName('./components/custom-table/custom-table'),
		  PLATFORM.moduleName('./components/google-signin-button/google-signin-button')
      ]
  )
}
