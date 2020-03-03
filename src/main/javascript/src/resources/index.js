export function configure(config) {
  config.globalResources(
      [
          PLATFORM.moduleName('./components/header/header'),
		      PLATFORM.moduleName('./components/custom-table/custom-table')
      ]
  )
}
